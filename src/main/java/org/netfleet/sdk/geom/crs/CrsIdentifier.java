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

package org.netfleet.sdk.geom.crs;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CrsIdentifier {

  final static public CrsIdentifier EPSG4326 =
      new CrsIdentifier("EPSG", 4326);

  private String authority;
  private int code;

  private CrsIdentifier() {
  }

  public CrsIdentifier(String authority, int code) {
    this.authority = authority;
    this.code = code;
  }

  public CrsIdentifier(String urn) {
    Pattern MY_PATTERN = Pattern.compile("urn:ogc:def:crs:(.*)::(.*)");
    Matcher m = MY_PATTERN.matcher(urn);
    if (m.find()) {
      this.authority = m.group(1);
      this.code = Integer.parseInt(m.group(2));
    } else {
      throw new IllegalArgumentException("Expected urn pattern: urn:ogc:def:crs:(.*)::(.*)");
    }
  }

  @Override
  public String toString() {
    return String.format("%S:%d", authority, code);
  }

  @JsonValue
  public String toUrn() {
    return String.format("urn:ogc:def:crs:%s::%s", authority, code);
  }

  public void setCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return authority;
  }
}
