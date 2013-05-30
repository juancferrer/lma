from google.appengine.ext import endpoints
import api

application = endpoints.api_server([api.Music,], restricted=False)
