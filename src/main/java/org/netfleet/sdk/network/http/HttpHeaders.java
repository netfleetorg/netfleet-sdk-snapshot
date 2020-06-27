/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 *  Copyright (C) 2020 Terra Yazılım Ltd Şti - All Rights Reserved
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Please contact Terra Yazılım, Konya Teknoloji Geliştirme Bölgesi
 *  Büyük Kayacık Mah. 101. Cad. No:2 42250 - Selçuklu Konya or visit
 *  www.terrayazilim.com.tr if you need additional information or have
 *  any questions.
 *
 */
package org.netfleet.sdk.network.http;

import java.util.*;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class HttpHeaders implements Iterable<Map.Entry<String, List<String>>>{

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.1">HTTP/1.1
   * documentation</a>}.
   */
  public static String ACCEPT = "Accept";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.2">HTTP/1.1
   * documentation</a>}.
   */
  public static String ACCEPT_CHARSET = "Accept-Charset";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.3">HTTP/1.1
   * documentation</a>}.
   */
  public static String ACCEPT_ENCODING = "Accept-Encoding";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.4">HTTP/1.1
   * documentation</a>}.
   */
  public static String ACCEPT_LANGUAGE = "Accept-Language";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.7">HTTP/1.1
   * documentation</a>}.
   */
  public static String ALLOW = "Allow";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.8">HTTP/1.1
   * documentation</a>}.
   */
  public static String AUTHORIZATION = "Authorization";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.9">HTTP/1.1
   * documentation</a>}.
   */
  public static String CACHE_CONTROL = "Cache-Control";

  /**
   * See {@link <a href="http://tools.ietf.org/html/rfc2183">IETF
   * RFC-2183</a>}.
   */
  public static String CONTENT_DISPOSITION = "Content-Disposition";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.11">HTTP/1.1
   * documentation</a>}.
   */
  public static String CONTENT_ENCODING = "Content-Encoding";

  /**
   * See {@link <a href="http://tools.ietf.org/html/rfc2392">IETF
   * RFC-2392</a>}.
   */
  public static String CONTENT_ID = "Content-ID";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.12">HTTP/1.1
   * documentation</a>}.
   */
  public static String CONTENT_LANGUAGE = "Content-Language";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.13">HTTP/1.1
   * documentation</a>}.
   */
  public static String CONTENT_LENGTH = "Content-Length";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.14">HTTP/1.1
   * documentation</a>}.
   */
  public static String CONTENT_LOCATION = "Content-Location";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.17">HTTP/1.1
   * documentation</a>}.
   */
  public static String CONTENT_TYPE = "Content-Type";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.18">HTTP/1.1
   * documentation</a>}.
   */
  public static String DATE = "Date";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.19">HTTP/1.1
   * documentation</a>}.
   */
  public static String ETAG = "ETag";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.21">HTTP/1.1
   * documentation</a>}.
   */
  public static String EXPIRES = "Expires";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.23">HTTP/1.1
   * documentation</a>}.
   */
  public static String HOST = "Host";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.24">HTTP/1.1
   * documentation</a>}.
   */
  public static String IF_MATCH = "If-Match";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.25">HTTP/1.1
   * documentation</a>}.
   */
  public static String IF_MODIFIED_SINCE = "If-Modified-Since";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.26">HTTP/1.1
   * documentation</a>}.
   */
  public static String IF_NONE_MATCH = "If-None-Match";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.28">HTTP/1.1
   * documentation</a>}.
   */
  public static String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.29">HTTP/1.1
   * documentation</a>}.
   */
  public static String LAST_MODIFIED = "Last-Modified";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.30">HTTP/1.1
   * documentation</a>}.
   */
  public static String LOCATION = "Location";

  /**
   * See {@link <a href="http://tools.ietf.org/html/rfc5988#page-6">Web
   * Linking (IETF RFC-5988) documentation</a>}.
   */
  public static String LINK = "Link";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.37">HTTP/1.1
   * documentation</a>}.
   */
  public static String RETRY_AFTER = "Retry-After";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.43">HTTP/1.1
   * documentation</a>}.
   */
  public static String USER_AGENT = "User-Agent";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.44">HTTP/1.1
   * documentation</a>}.
   */
  public static String VARY = "Vary";

  /**
   * See {@link
   * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.47">HTTP/1.1
   * documentation</a>}.
   */
  public static String WWW_AUTHENTICATE = "WWW-Authenticate";

  /**
   * See {@link <a href="http://www.ietf.org/rfc/rfc2109.txt">IETF RFC
   * 2109</a>}.
   */
  public static String COOKIE = "Cookie";

  /**
   * See {@link <a href="http://www.ietf.org/rfc/rfc2109.txt">IETF RFC
   * 2109</a>}.
   */
  public static String SET_COOKIE = "Set-Cookie";

  /**
   * {@code "Last-Event-ID"} HTTP request header name as defined by
   * <a href="http://www.w3.org/TR/eventsource/#last-event-id">SSE
   * specification</a>.
   *
   * @since 2.1
   */
  public static String LAST_EVENT_ID_HEADER = "Last-Event-ID";

  private final Map<String, List<String>> headers;

  public HttpHeaders() {
    this.headers = new LinkedHashMap<>();
  }

  public HttpHeaders add(String key, String value) {
    if (!headers.containsKey(key)) {
      headers.put(key, new ArrayList<String>());
    }

    headers.get(key).add(value);

    return this;
  }

  public HttpHeaders add(Map<String, List<String>> multiMap) {
    for (Map.Entry<String, List<String>> entry: multiMap.entrySet()) {
      String key = entry.getKey();

      for (String value: entry.getValue()) {
        if (!headers.containsKey(key)) {
          headers.put(key, new ArrayList<String>());
        }

        headers.get(key).add(value);
      }
    }

    return this;
  }

  public boolean has(String key, String value) {
    Collection<String> collection = headers.get(key);
    if (collection == null)
      return false;

    boolean found = false;
    for (String each: collection) {
      if (each.equals(value)) {
        found = true;
        break;
      }
    }

    return found;
  }

  public boolean hasKey(String key) {
    return headers.containsKey(key);
  }

  public boolean hasValue(String value) {
    boolean found = false;
    for (Collection<String> collection: headers.values()) {
      for (String each: collection) {
        if (each.equals(value)) {
          found = true;
          break;
        }
      }
    }

    return found;
  }

  @Override
  public Iterator<Map.Entry<String, List<String>>> iterator() {
    return headers.entrySet().iterator();
  }
}
