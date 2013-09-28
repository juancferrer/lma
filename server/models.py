import datetime
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
    identifier = ndb.StringProperty(required=True)

    def _pre_put_hook(self,):
        self.key = ndb.Key(self.__class__, self.identifier)
        self.search_fields = self.name.split(' ')
        self.search_fields.extend([name.lower() for name in self.name.split(' ')])
        self.search_fields.extend(slugify(self.name).split('-'))
        self.search_fields = set(self.search_fields)

    def to_message(self,):
        return ArtistResponse(name=self.name, show_count=self.show_count,
                identifier=self.identifier)

    def __hash__(self,):
        m = hashlib.md5()
        m.update(self.key.urlsafe())
        return int(m.hexdigest(), 16)

class ShowRecording(ndb.Model):
    artist = ndb.KeyProperty(kind=Artist)
    title = ndb.StringProperty(required=True)
    identifier = ndb.StringProperty(required=True)
    description = ndb.StringProperty(indexed=False)
    date = ndb.DateTimeProperty(required=True)
    source = ndb.StringProperty(indexed=False)
    downloads = ndb.IntegerProperty()
    coverage = ndb.StringProperty(repeated=True)
    subject = ndb.StringProperty(repeated=True)
    search_fields = ndb.StringProperty(repeated=True)

    def _pre_put_hook(self,):
        self.key = ndb.Key(self.__class__, self.identifier)
        self.search_fields = self.title.split(' ')
        self.search_fields.extend([name.lower() for name in self.title.split(' ')])
        self.search_fields.extend(slugify(self.title).split('-'))
        self.search_fields.extend([x.lower().strip() for x in self.coverage])
        self.search_fields.extend([x.lower() for x in self.subject])
        self.search_fields = set(self.search_fields)

    def to_message(self,):
        return ShowRecordingResponse(artist=self.key.id(), title=self.title,
                identifier=self.identifier, description=self.description,
                source=self.source, downloads=self.downloads,
                coverage=self.coverage, subject=self.subject,
                date=self.date.isoformat())

    def __hash__(self,):
        m = hashlib.md5()
        m.update(self.key.urlsafe())
        return int(m.hexdigest(), 16)
