from google.appengine.ext import endpoints
from protorpc import remote
from models import Artist
from messages import ArtistRequest, ArtistsResponse

CLIENT_ID = 'live-music-archive'

@endpoints.api(name='music', version='v1',
               description='Live Music Archive API',
               allowed_client_ids=[CLIENT_ID, endpoints.API_EXPLORER_CLIENT_ID])
class Music(remote.Service):
    '''Defines the music api v1'''

    @endpoints.method(ArtistRequest, ArtistsResponse,
                      path='artists', http_method='GET',
                      name='music.artists')
    def artists(self, request):
        '''API endpoint to query for artists'''
        artists = [artist.to_message() for artist in Artist.query().order(+Artist.key)]
        return ArtistsResponse(artists=artists)
