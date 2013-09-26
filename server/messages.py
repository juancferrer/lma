from protorpc import messages
from protorpc import message_types

class ArtistsRequest(messages.Message):
    next_page = messages.StringField(1, required=False)


class ArtistResponse(messages.Message):
    name = messages.StringField(1, required=True)
    show_count = messages.IntegerField(2, required=True)
    identifier = messages.StringField(3, required=True)


class ArtistsResponse(messages.Message):
   next_page = messages.StringField(1, required=False)
   artists = messages.MessageField(ArtistResponse, 2, repeated=True) 

class ShowRecordingResponse(messages.Message):
    artist = messages.StringField(1, required=True)
    title = messages.StringField(2, required=True)
    identifier = messages.StringField(3, required=True)
    description = messages.StringField(4)
    date = message_types.DateTimeField(5)
    source = messages.StringField(6)
    downloads = messages.IntegerField(7)
    coverage = messages.StringField(8, repeated=True)
    subject = messages.StringField(9, repeated=True)


class SearchRequest(messages.Message):
    query = messages.StringField(1, required=True)


class SearchResponse(messages.Message):
    artists = messages.MessageField(ArtistResponse, 1, repeated=True)
    shows = messages.MessageField(ShowRecordingResponse, 2, repeated=True)
    #songs = messages.MessageField(ArtistResponse, 1, repeated=True)


