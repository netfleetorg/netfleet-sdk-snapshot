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
package org.netfleet.sdk.integration.graphhopper;

import java.io.Serializable;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class GraphhopperGeocodingContext implements Serializable {

  private String coordinate;
  private String address;
  private String locale = "tr";
  private String provider = "default";
  private int limit = 5;

  public GraphhopperGeocodingContext() {
  }

  public GraphhopperGeocodingContext(Builder b) {
    this.coordinate = b.coordinate;
    this.address = b.address;
    this.locale = b.locale;
    this.provider = b.provider;
    this.limit = b.limit;
  }

  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }

  public String getCoordinate() {
    return coordinate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  /**
   * @version 1.0.0-RELEASE
   * @since 1.0.0-RELEASE
   */
  public static class Builder {
    private String coordinate;
    private String address;
    private String locale = "tr";
    private String provider = "default";
    private int limit = 5;

    public Builder setCoordinate(String coordinate) {
      this.coordinate = coordinate;
      return this;
    }

    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    public Builder setLimit(int limit) {
      this.limit = limit;
      return this;
    }

    public Builder setLocale(String locale) {
      this.locale = locale;
      return this;
    }

    public Builder setProvider(String provider) {
      this.provider = provider;
      return this;
    }

    public GraphhopperGeocodingContext build() {
      return new GraphhopperGeocodingContext(this);
    }
  }

}
