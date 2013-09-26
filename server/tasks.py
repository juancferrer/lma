import json
import webapp2
import datetime
from hashlib import sha1

from google.appengine.api import taskqueue
from google.appengine.api import memcache
from google.appengine.api import urlfetch
from google.appengine.ext import deferred
from BeautifulSoup import BeautifulSoup
from models import Artist, ShowRecording

ARTISTS_URL = 'http://www.archive.org/browse.php?collection=etree&field=%2Fmetadata%2Fcreator'
SHOWS_URL = 'http://archive.org/advancedsearch.php?q=collection%3A{0}&fl%5B%5D=collection&fl%5B%5D=date&fl%5B%5D=rating&fl%5B%5D=source&fl%5B%5D=coverage&fl%5B%5D=description&fl%5B%5D=identifier&fl%5B%5D=downloads&fl%5B%5D=title&fl%5B%5D=subject&sort%5B%5D=&sort%5B%5D=&sort%5B%5D=&rows=50&page={1}&output=json&callback=callback&save=yes'


def fetch_artist():
    '''Fetches live music archive page, scrapes artist info,
    and saves to db'''
    result = urlfetch.fetch(ARTISTS_URL)
    if result.status_code != 200:
        return
    soup = BeautifulSoup(result.content.decode('utf-8')) 
    artists_table = soup.find(id='browse')
    items = artists_table.findAll('a')
    for i in range(len(items)-1)[::2]:
        name = items[i].contents[0]
        identifier = items[i].get('href').split('/')[-1]
        shows = int(items[i+1].string.split(' ')[0].replace(',','').encode('utf-8'))
        artist = Artist(name=name, show_count=shows, identifier=identifier)
        print artist
        artist.put()


class ScrapeArtist(webapp2.RequestHandler):
    '''Uses a deferred to scrape data from live music archive'''
    def get(self,):
        deferred.defer(fetch_artist,)

def resave():
    for artist in Artist.query().iter():
        artist.put()

class Resave(webapp2.RequestHandler):
    def get(self,):
        deferred.defer(resave,)

def fetch_shows(artist, next_page=1):
    url = SHOWS_URL.format(artist.id(), next_page)
    result = urlfetch.fetch(url)
    if result.status_code != 200:
        return
    data =  json.loads(result.content[9:-1])
    for doc in data['response']['docs']:
        show = ShowRecording(artist=artist,
                title=doc.get('title',''), 
                identifier=doc.get('identifier',''),
                description=doc.get('description',''), 
                source=doc.get('source',''), 
                downloads=int(doc.get('downloads',0)), 
                coverage=doc.get('coverage',[]),
                subject=doc.get('subject',[]),
                date=datetime.datetime.strptime(doc.get('date', '1500-01-01T00:00:00Z'), '%Y-%m-%dT%H:%M:%SZ'))
        show.put()
    if next_page==1:
        count = int(data['response']['numFound'])
        if count > 50:
            for page in range(2, (count / 50)+1):
                deferred.defer(fetch_shows, artist, next_page=page)

def begin_fetch_shows():
    for artist in Artist.query().fetch(keys_only=True):
        deferred.defer(fetch_shows, artist)

class ScrapeShows(webapp2.RequestHandler):
    def get(self):
        deferred.defer(begin_fetch_shows)

app = webapp2.WSGIApplication([
    #('/.*', Resave),
    ('/.*', ScrapeShows),
    ],
     debug=True)
