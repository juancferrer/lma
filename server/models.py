import hashlib
from google.appengine.ext import ndb

from messages import *
from utils import slugify


class Images(ndb.Model):
    kind = ndb.StringProperty(required=True, choices=['icon', 'large'])
    url = ndb.StringProperty(required=True)


class Artist(ndb.Model):
    name = ndb.StringProperty(required=True)
    images = ndb.StructuredProperty(Images, repeated=True)
    show_count = ndb.IntegerProperty(required=True)
    search_fields = ndb.StringProperty(repeated=True)

    def _pre_put_hook(self,):
        self.key = ndb.Key(self.__class__, slugify(self.name))
        self.search_fields = self.name.split(' ')
        self.search_fields.extend([name.lower() for name in self.name.split(' ')])
        self.search_fields.extend(slugify(self.name).split('-'))
        self.search_fields = set(self.search_fields)

    def to_message(self,):
        return ArtistResponse(name=self.name, show_count=self.show_count)

    def __hash__(self,):
        m = hashlib.md5()
        m.update(self.key.urlsafe())
        return int(m.hexdigest(), 16)
