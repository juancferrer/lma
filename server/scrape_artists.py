import json
import webapp2
from hashlib import sha1

from google.appengine.api import taskqueue
from google.appengine.api import memcache
from google.appengine.api import urlfetch
from google.appengine.ext import deferred

from BeautifulSoup import BeautifulSoup
from models import Artist

ARTISTS_URL = 'http://www.archive.org/browse.php?collection=etree&field=%2Fmetadata%2Fcreator'


def fetch_data():
    '''Fetches live music archive page, scrapes artist info,
    and saves to db'''
    result = urlfetch.fetch(ARTISTS_URL)
    if result.status_code != 200:
        return
    soup = BeautifulSoup(unicode(result.content))
    artists_table = soup.find(id='browse')
    items = artists_table.findAll('a')
    for i in range(len(items)-1)[::2]:
        name = items[i].string.encode('utf-8')
        shows = int(items[i+1].string.split(' ')[0].replace(',','').encode('utf-8'))
        artist = Artist(name=name, show_count=shows)
        artist.put()


class ScrapeData(webapp2.RequestHandler):
    '''Uses a deferred to scrape data from live music archive'''
    def get(self,):
        deferred.defer(fetch_data,)



app = webapp2.WSGIApplication([
    ('/.*', ScrapeData),
    ],
     debug=True)
