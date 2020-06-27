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
package org.netfleet.sdk.geom.geojson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.netfleet.sdk.geom.geojson.codec.JacksonCoordinateDeserializer;
import org.netfleet.sdk.geom.geojson.codec.JacksonCoordinateSerializer;

import java.io.Serializable;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @see <a href="http://geojson.org/geojson-spec.html#positions">Geojson Position Specification.</a>
 * @since 1.0.0-RELEASE
 */
@JsonSerialize(using = JacksonCoordinateSerializer.class)
@JsonDeserialize(using = JacksonCoordinateDeserializer.class)
public class GeojsonCoordinate implements Serializable {

  private double latitude = Double.NaN;
  private double longitude = Double.NaN;
  private double altitude = Double.NaN;

  private double[] additionalElements = new double[0];

  public GeojsonCoordinate() {
  }

  public GeojsonCoordinate(double latitude, double longitude) {
    this(latitude, longitude, Double.NaN, new double[0]);
  }

  public GeojsonCoordinate(double latitude, double longitude, double altitude) {
    this(latitude, longitude, altitude, new double[0]);
  }

  public GeojsonCoordinate(double latitude, double longitude, double altitude, double[] additionalElements) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.altitude = altitude;
    this.additionalElements = additionalElements;
  }

  public boolean hasAltitude() {
    return !Double.isNaN(getAltitude());
  }

  public boolean hasAdditionalElements() {
    return getAdditionalElements().length > 0;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getAltitude() {
    return altitude;
  }

  public void setAltitude(double altitude) {
    this.altitude = altitude;
  }

  public double[] getAdditionalElements() {
    return additionalElements;
  }

  public void setAdditionalElements(double[] additionalElements) {
    this.additionalElements = additionalElements;
  }

}
