from google.appengine.ext import ndb
from django.template.defaultfilters import slugify

from messages import *


class Images(ndb.Model):
    kind = ndb.StringProperty(required=True, choices=['icon', 'large'])
    url = ndb.StringProperty(required=True)


class Artist(ndb.Model):
    name = ndb.StringProperty(required=True)
    images = ndb.StructuredProperty(Images, repeated=True)
    show_count = ndb.IntegerProperty(required=True)

    def _pre_put_hook(self,):
        self.key = ndb.Key(self.__class__, slugify(self.name))

    def to_message(self,):
        return ArtistResponse(name=self.name, show_count=self.show_count)
