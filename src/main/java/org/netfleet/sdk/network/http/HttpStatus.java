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

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpStatus implements Serializable {

  private final String definition;
  private final Integer code;

  public HttpStatus() {
    this(null, null);
  }

  public HttpStatus(Integer code) {
    this(null, code);
  }

  public HttpStatus(String definition, Integer code) {
    this.definition = definition;
    this.code = code;
  }

  public HttpStatus(HttpStatuses status) {
    this(status.getPhrase(), status.getCode());
  }

  public static HttpStatus of(Integer code) {
    return new HttpStatus(code);
  }

  public static HttpStatus of(Integer code, String definition) {
    return new HttpStatus(definition, code);
  }

  public String getDefinition() {
    return definition;
  }

  public Integer getCode() {
    return code;
  }

}
