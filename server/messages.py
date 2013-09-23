from protorpc import messages

class ArtistsRequest(messages.Message):
    next_page = messages.StringField(1, required=False)
    name = messages.StringField(2, required=False)


class ArtistResponse(messages.Message):
    name = messages.StringField(1, required=True)
    show_count = messages.IntegerField(2, required=True)


class ArtistsResponse(messages.Message):
   next_page = messages.StringField(1, required=False)
   artists = messages.MessageField(ArtistResponse, 2, repeated=True) 


class SearchRequest(messages.Message):
    query = messages.StringField(1, required=True)


class SearchResponse(messages.Message):
    artists = messages.MessageField(ArtistResponse, 1, repeated=True)
    #shows = messages.MessageField(ArtistResponse, 1, repeated=True)
    #songs = messages.MessageField(ArtistResponse, 1, repeated=True)

