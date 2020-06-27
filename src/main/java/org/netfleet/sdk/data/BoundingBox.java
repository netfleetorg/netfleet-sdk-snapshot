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
package org.netfleet.sdk.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.netfleet.sdk.data.json.JacksonBoundingBoxDeserializer;
import org.netfleet.sdk.data.json.JacksonBoundingBoxSerializer;

import java.io.Serializable;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@JsonSerialize(using = JacksonBoundingBoxSerializer.class)
@JsonDeserialize(using = JacksonBoundingBoxDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoundingBox implements Serializable {

  private Long id;
  private Double southWestLatitude;
  private Double southWestLongitude;
  private Double northEastLatitude;
  private Double northEastLongitude;

  public BoundingBox() {
  }

  public BoundingBox(Long id, Double southWestLatitude, Double southWestLongitude, Double northEastLatitude, Double northEastLongitude) {
    this.id = id;
    this.southWestLatitude = southWestLatitude;
    this.southWestLongitude = southWestLongitude;
    this.northEastLatitude = northEastLatitude;
    this.northEastLongitude = northEastLongitude;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getSouthWestLatitude() {
    return southWestLatitude;
  }

  public void setSouthWestLatitude(Double southWestLatitude) {
    this.southWestLatitude = southWestLatitude;
  }

  public Double getSouthWestLongitude() {
    return southWestLongitude;
  }

  public void setSouthWestLongitude(Double southWestLongitude) {
    this.southWestLongitude = southWestLongitude;
  }

  public Double getNorthEastLatitude() {
    return northEastLatitude;
  }

  public void setNorthEastLatitude(Double northEastLatitude) {
    this.northEastLatitude = northEastLatitude;
  }

  public Double getNorthEastLongitude() {
    return northEastLongitude;
  }

  public void setNorthEastLongitude(Double northEastLongitude) {
    this.northEastLongitude = northEastLongitude;
  }
}
