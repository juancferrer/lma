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
