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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
  @JsonSubTypes.Type(GeojsonPoint.class),
  @JsonSubTypes.Type(GeojsonMultiPoint.class),
  @JsonSubTypes.Type(GeojsonLineString.class),
  @JsonSubTypes.Type(GeojsonMultiLineString.class),
  @JsonSubTypes.Type(GeojsonPolygon.class),
  @JsonSubTypes.Type(GeojsonMultiPolygon.class),
  @JsonSubTypes.Type(GeojsonFeature.class),
  @JsonSubTypes.Type(GeojsonFeatureCollection.class),
  @JsonSubTypes.Type(GeojsonGeometryCollection.class),
})
public abstract class GeojsonObject implements Serializable {

  public abstract <T> T accept(GeojsonObjectVisitor<T> visitor);

  private GeojsonObjectType type;

  private GeojsonCrs crs;

  private double[] bbox;

  public void setType(GeojsonObjectType type) {
    this.type = type;
  }

  public void setCrs(GeojsonCrs crs) {
    this.crs = crs;
  }

  public void setBbox(double[] bbox) {
    this.bbox = bbox;
  }

  public GeojsonObjectType getType() {
    return type;
  }

  public GeojsonCrs getCrs() {
    return crs;
  }

  public double[] getBbox() {
    return bbox;
  }

}
