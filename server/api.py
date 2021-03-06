from google.appengine.ext import endpoints, ndb
from google.appengine.api import memcache
from google.appengine.datastore.datastore_query import Cursor
from protorpc import remote
from models import Artist, ShowRecording
from messages import (ArtistsRequest, ArtistsResponse,
                      SearchRequest, SearchResponse, ShowRecordingResponse)

CLIENT_ID = 'live-music-archive'

@endpoints.api(name='music', version='v1',
               description='Live Music Archive API',
               package_path='com/micronixsolutions/api',
               allowed_client_ids=[CLIENT_ID, endpoints.API_EXPLORER_CLIENT_ID])
class Music(remote.Service):
    '''Defines the music api v1'''

    @endpoints.method(ArtistsRequest, ArtistsResponse,
                      path='artists', http_method='GET',
                      name='music.artists')
    def artists(self, request):
        '''API endpoint to query for artists'''
        next_page = request.next_page or 'first_artists_page'
        cache = memcache.get(next_page)
        if cache:
            return ArtistsResponse(artists=cache[0], next_page=cache[1])
        query = Artist.query()
        if next_page is 'first_artists_page':
            artists, cursor, more = query.fetch_page(300) 
        else:
            artists, cursor, more = query.fetch_page(300, 
                    start_cursor=Cursor.from_websafe_string(next_page))
        artists = [artist.to_message() for artist in artists]
        memcache.add(next_page, (artists, cursor.to_websafe_string()))
        return ArtistsResponse(artists=artists, next_page=cursor.to_websafe_string())


    @endpoints.method(SearchRequest, SearchResponse,
                      path='search', http_method='GET',
                      name='search.all')
    @ndb.synctasklet
    def search_all(self, request):
        '''API endpoint to search for everything'''
        query_bits = request.query.split(' ')
        artists = yield self.create_search_query(Artist, Artist.search_fields, query_bits)
        shows = yield self.create_search_query(ShowRecording, ShowRecording.search_fields, query_bits)
        #songs_query = Songs.query()
        artists = [artist.to_message() for artist in artists]
        shows = [show.to_message() for show in shows]
        raise ndb.Return(SearchResponse(artists=artists, shows=shows))

    @ndb.tasklet
    def create_search_query(self, klass, attr, bits):
        '''Helper method to create query objects that do string
        comparison'''
        futures = []
        for bit in bits:
            futures.append(klass.query(ndb.AND(attr >= bit, attr < bit+u'\ufffd')).fetch_async())
            
        results = []
        for future in futures:
            results.append(set(future.get_result()))
        raise ndb.Return(set.intersection(*results))

