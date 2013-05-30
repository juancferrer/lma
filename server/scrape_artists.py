import json
import webapp2
from hashlib import sha1

from google.appengine.api import taskqueue
from google.appengine.api import memcache
from google.appengine.api import urlfetch
from google.appengine.ext import deferred
from django.utils.encoding import smart_unicode


from BeautifulSoup import BeautifulSoup
from .models import Artist

ARTISTS_URL = 'http://www.archive.org/browse.php?collection=etree&field=%2Fmetadata%2Fcreator'

def update_cache():
    artists = Artist.all()
    artists.order('name')
    json_str = json.dumps([artist.to_dict() for artist in artists])
    memcache.set(key='artists', value=json_str)

def create_artist(key, name, show_count):
        show_count = int(show_count)
        artist = Artist(key_name=key, name=name, show_count=show_count)
        artist.put()


def fetch_data():
    result = urlfetch.fetch(ARTISTS_URL)
    if result.status_code != 200:
        return
    soup = BeautifulSoup(smart_unicode(result.content))
    artists_table = soup.find(id='browse')
    items = artists_table.findAll('a')
    for i in range(len(items)-1)[::2]:
        name = smart_unicode(items[i].string.encode('utf-8'))
        key = sha1(name.encode('utf-8')).hexdigest()
        shows = int(items[i+1].string.split(' ')[0].replace(',','').encode('utf-8'))
        #deferred.defer(create_artist, key=key, name=name, show_count=shows)
        artist = Artist(key_name=key, name=name, show_count=shows)
        artist.put()
        #taskqueue.add(url='/task/scrape/artist/create', 
        #        params={'key':key, 'name':name, 'show_count':shows}) 
    # Skip updating cache for now
    # deferred.defer(update_cache)


class ScrapeData(webapp2.RequestHandler):
    def get(self,):
        deferred.defer(fetch_data,)



app = webapp2.WSGIApplication([
    ('/.*', ScrapeData),
    ],
     debug=True)
