import json
import webapp2

from google.appengine.api import memcache

from models import Artist

class MainPage(webapp2.RequestHandler):
    def get(self):
        json_str = memcache.get(key='artists')
        if not json_str:
            artists = Artist.all()
            artists.order('name')
            json_str = json.dumps([artist.to_dict() for artist in artists])
            memcache.set(key='artists', value=json_str)
        self.response.out.write(json_str)
        self.response.headers['Content-Type'] = 'text/plain'
app = webapp2.WSGIApplication([('/.*', MainPage)], debug=True)
