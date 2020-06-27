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

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public enum HttpStatuses {

  OK(200, "OK"),
  CREATED(201, "Created"),
  ACCEPTED(202, "Accepted"),
  NO_CONTENT(204, "No Content"),
  RESET_CONTENT(205, "Reset Content"),
  PARTIAL_CONTENT(206, "Partial Content"),
  MOVED_PERMANENTLY(301, "Moved Permanently"),
  FOUND(302, "Found"),
  SEE_OTHER(303, "See Other"),
  NOT_MODIFIED(304, "Not Modified"),
  USE_PROXY(305, "Use Proxy"),
  TEMPORARY_REDIRECT(307, "Temporary Redirect"),
  BAD_REQUEST(400, "Bad Request"),
  UNAUTHORIZED(401, "Unauthorized"),
  PAYMENT_REQUIRED(402, "Payment Required"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not Found"),
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
  NOT_ACCEPTABLE(406, "Not Acceptable"),
  PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
  REQUEST_TIMEOUT(408, "Request Timeout"),
  CONFLICT(409, "Conflict"),
  GONE(410, "Gone"),
  LENGTH_REQUIRED(411, "Length Required"),
  PRECONDITION_FAILED(412, "Precondition Failed"),
  REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
  REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
  UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
  REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
  EXPECTATION_FAILED(417, "Expectation Failed"),
  IM_A_TEAPOT(418, "I'm a Teapot"),
  UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
  TOO_MANY_REQUESTS(429, "Too Many Requests"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
  NOT_IMPLEMENTED(501, "Not Implemented"),
  BAD_GATEWAY(502, "Bad Gateway"),
  SERVICE_UNAVAILABLE(503, "Service Unavailable"),
  GATEWAY_TIMEOUT(504, "Gateway Timeout");

  private final String phrase;
  private final int code;

  HttpStatuses(final int code, final String phrase) {
    this.phrase = phrase;
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }

  public String getPhrase() {
    return phrase;
  }

  public HttpStatusFamily getFamily() {
    if (code >= 100 && code < 200) {
      return HttpStatusFamily.INFORMATIONAL;
    } else if (code >= 200 && code < 300) {
      return HttpStatusFamily.SUCCESSFUL;
    } else if (code >= 300 && code < 400) {
      return HttpStatusFamily.REDIRECTION;
    } else if (code >= 400 && code < 500) {
      return HttpStatusFamily.CLIENT_ERROR;
    } else if (code >= 500 && code < 600) {
      return HttpStatusFamily.SERVER_ERROR;
    } else {
      return HttpStatusFamily.OTHER;
    }
  }

}