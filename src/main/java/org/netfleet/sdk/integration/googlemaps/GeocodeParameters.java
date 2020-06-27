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
package org.netfleet.sdk.integration.googlemaps;

import java.io.Serializable;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class GeocodeParameters implements Serializable {

  private final String apiKey;
  private String coordinate;
  private String address;

  public GeocodeParameters(Builder builder) {
    this(builder.apiKey);
    this.coordinate = builder.coordinate;
    this.address = builder.address;
  }

  public GeocodeParameters(String apiKey) {
    if (apiKey == null) {
      throw new IllegalArgumentException("apiKey cannot be null.");
    }

    this.apiKey = apiKey;
  }

  public String getApiKey() {
    return apiKey;
  }

  public String getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
   */
  public static class Builder {
    private String apiKey;
    private String coordinate;
    private String address;

    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    public Builder setApiKey(String apiKey) {
      this.apiKey = apiKey;
      return this;
    }

    public Builder setCoordinate(String coordinate) {
      this.coordinate = coordinate;
      return this;
    }

    public GeocodeParameters build() {
      return new GeocodeParameters(this);
    }
  }
}
