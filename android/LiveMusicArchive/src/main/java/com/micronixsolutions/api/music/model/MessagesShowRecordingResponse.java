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

package com.micronixsolutions.api.music.model;

/**
 * Model definition for MessagesShowRecordingResponse.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the music. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class MessagesShowRecordingResponse extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String artist;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> coverage;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String date;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String description;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long downloads;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String identifier;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String source;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> subject;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String title;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getArtist() {
    return artist;
  }

  /**
   * @param artist artist or {@code null} for none
   */
  public MessagesShowRecordingResponse setArtist(java.lang.String artist) {
    this.artist = artist;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getCoverage() {
    return coverage;
  }

  /**
   * @param coverage coverage or {@code null} for none
   */
  public MessagesShowRecordingResponse setCoverage(java.util.List<java.lang.String> coverage) {
    this.coverage = coverage;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDate() {
    return date;
  }

  /**
   * @param date date or {@code null} for none
   */
  public MessagesShowRecordingResponse setDate(java.lang.String date) {
    this.date = date;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * @param description description or {@code null} for none
   */
  public MessagesShowRecordingResponse setDescription(java.lang.String description) {
    this.description = description;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getDownloads() {
    return downloads;
  }

  /**
   * @param downloads downloads or {@code null} for none
   */
  public MessagesShowRecordingResponse setDownloads(java.lang.Long downloads) {
    this.downloads = downloads;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIdentifier() {
    return identifier;
  }

  /**
   * @param identifier identifier or {@code null} for none
   */
  public MessagesShowRecordingResponse setIdentifier(java.lang.String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSource() {
    return source;
  }

  /**
   * @param source source or {@code null} for none
   */
  public MessagesShowRecordingResponse setSource(java.lang.String source) {
    this.source = source;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getSubject() {
    return subject;
  }

  /**
   * @param subject subject or {@code null} for none
   */
  public MessagesShowRecordingResponse setSubject(java.util.List<java.lang.String> subject) {
    this.subject = subject;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTitle() {
    return title;
  }

  /**
   * @param title title or {@code null} for none
   */
  public MessagesShowRecordingResponse setTitle(java.lang.String title) {
    this.title = title;
    return this;
  }

  @Override
  public MessagesShowRecordingResponse set(String fieldName, Object value) {
    return (MessagesShowRecordingResponse) super.set(fieldName, value);
  }

  @Override
  public MessagesShowRecordingResponse clone() {
    return (MessagesShowRecordingResponse) super.clone();
  }

}
