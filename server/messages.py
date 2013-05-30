from protorpc import messages

class ArtistRequest(messages.Message):
    name = messages.StringField(1, required=False)


class ArtistResponse(messages.Message):
    name = messages.StringField(1, required=True)
    show_count = messages.IntegerField(2, required=True)


class ArtistsResponse(messages.Message):
   artists = messages.MessageField(ArtistResponse, 1, repeated=True) 
