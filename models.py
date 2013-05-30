import datetime
from google.appengine.ext import db


class Artist(db.Model):
    name = db.StringProperty(required=True)
    image_url = db.StringProperty()
    show_count = db.IntegerProperty(required=True)

    def to_dict(self,):
        return {'name': self.name, 'image': self.image_url,
                'shows': self.show_count}
