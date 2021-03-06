/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-09-16 16:01:30 UTC)
 * on 2013-09-27 at 05:52:10 UTC 
 * Modify at your own risk.
 */

package com.micronixsolutions.api.music;

/**
 * Service definition for Music (v1).
 *
 * <p>
 * Live Music Archive API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link MusicRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Music extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.17.0-rc of the music library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://live-music-archive.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "music/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Music(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Music(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * An accessor for creating requests from the MusicOperations collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Music music = new Music(...);}
   *   {@code Music.MusicOperations.List request = music.music().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public MusicOperations music() {
    return new MusicOperations();
  }

  /**
   * The "music" collection of methods.
   */
  public class MusicOperations {

    /**
     * API endpoint to query for artists
     *
     * Create a request for the method "music.artists".
     *
     * This request holds the parameters needed by the music server.  After setting any optional
     * parameters, call the {@link Artists#execute()} method to invoke the remote operation.
     *
     * @return the request
     */
    public Artists artists() throws java.io.IOException {
      Artists result = new Artists();
      initialize(result);
      return result;
    }

    public class Artists extends MusicRequest<com.micronixsolutions.api.music.model.MessagesArtistsResponse> {

      private static final String REST_PATH = "artists";

      /**
       * API endpoint to query for artists
       *
       * Create a request for the method "music.artists".
       *
       * This request holds the parameters needed by the the music server.  After setting any optional
       * parameters, call the {@link Artists#execute()} method to invoke the remote operation. <p>
       * {@link
       * Artists#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
       * be called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected Artists() {
        super(Music.this, "GET", REST_PATH, null, com.micronixsolutions.api.music.model.MessagesArtistsResponse.class);
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public Artists setAlt(java.lang.String alt) {
        return (Artists) super.setAlt(alt);
      }

      @Override
      public Artists setFields(java.lang.String fields) {
        return (Artists) super.setFields(fields);
      }

      @Override
      public Artists setKey(java.lang.String key) {
        return (Artists) super.setKey(key);
      }

      @Override
      public Artists setOauthToken(java.lang.String oauthToken) {
        return (Artists) super.setOauthToken(oauthToken);
      }

      @Override
      public Artists setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (Artists) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public Artists setQuotaUser(java.lang.String quotaUser) {
        return (Artists) super.setQuotaUser(quotaUser);
      }

      @Override
      public Artists setUserIp(java.lang.String userIp) {
        return (Artists) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key("next_page")
      private java.lang.String nextPage;

      /**

       */
      public java.lang.String getNextPage() {
        return nextPage;
      }

      public Artists setNextPage(java.lang.String nextPage) {
        this.nextPage = nextPage;
        return this;
      }

      @Override
      public Artists set(String parameterName, Object value) {
        return (Artists) super.set(parameterName, value);
      }
    }

  }

  /**
   * An accessor for creating requests from the Search collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Music music = new Music(...);}
   *   {@code Music.Search.List request = music.search().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public Search search() {
    return new Search();
  }

  /**
   * The "search" collection of methods.
   */
  public class Search {

    /**
     * API endpoint to search for everything
     *
     * Create a request for the method "search.all".
     *
     * This request holds the parameters needed by the music server.  After setting any optional
     * parameters, call the {@link All#execute()} method to invoke the remote operation.
     *
     * @param query
     * @return the request
     */
    public All all(java.lang.String query) throws java.io.IOException {
      All result = new All(query);
      initialize(result);
      return result;
    }

    public class All extends MusicRequest<com.micronixsolutions.api.music.model.MessagesSearchResponse> {

      private static final String REST_PATH = "search";

      /**
       * API endpoint to search for everything
       *
       * Create a request for the method "search.all".
       *
       * This request holds the parameters needed by the the music server.  After setting any optional
       * parameters, call the {@link All#execute()} method to invoke the remote operation. <p> {@link
       * All#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must be
       * called to initialize this instance immediately after invoking the constructor. </p>
       *
       * @param query
       * @since 1.13
       */
      protected All(java.lang.String query) {
        super(Music.this, "GET", REST_PATH, null, com.micronixsolutions.api.music.model.MessagesSearchResponse.class);
        this.query = com.google.api.client.util.Preconditions.checkNotNull(query, "Required parameter query must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public All setAlt(java.lang.String alt) {
        return (All) super.setAlt(alt);
      }

      @Override
      public All setFields(java.lang.String fields) {
        return (All) super.setFields(fields);
      }

      @Override
      public All setKey(java.lang.String key) {
        return (All) super.setKey(key);
      }

      @Override
      public All setOauthToken(java.lang.String oauthToken) {
        return (All) super.setOauthToken(oauthToken);
      }

      @Override
      public All setPrettyPrint(java.lang.Boolean prettyPrint) {
        return (All) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public All setQuotaUser(java.lang.String quotaUser) {
        return (All) super.setQuotaUser(quotaUser);
      }

      @Override
      public All setUserIp(java.lang.String userIp) {
        return (All) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private java.lang.String query;

      /**

       */
      public java.lang.String getQuery() {
        return query;
      }

      public All setQuery(java.lang.String query) {
        this.query = query;
        return this;
      }

      @Override
      public All set(String parameterName, Object value) {
        return (All) super.set(parameterName, value);
      }
    }

  }

  /**
   * Builder for {@link Music}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Music}. */
    @Override
    public Music build() {
      return new Music(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link MusicRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setMusicRequestInitializer(
        MusicRequestInitializer musicRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(musicRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
