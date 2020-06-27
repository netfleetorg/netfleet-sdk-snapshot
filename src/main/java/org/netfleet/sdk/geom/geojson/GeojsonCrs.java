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

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeojsonCrs implements Serializable {

  public static final String EPSG3857 = "urn:ogc:def:crs:EPSG::3857";
  public static final String EPSG4326 = "urn:ogc:def:crs:OGC:1.3:CRS84";

  private GeojsonCrsProperties properties = new GeojsonCrsProperties();
  private String type;

  public GeojsonCrs() {
  }

  public GeojsonCrs(String type, GeojsonCrsProperties properties) {
    this.type = type;
    this.properties = properties;
  }

  public static GeojsonCrs epsg3857() {
    final GeojsonCrs crs = new GeojsonCrs();
    crs.setType("name");
    crs.setProperties(new GeojsonCrsProperties());
    crs.getProperties().setName(EPSG3857);

    return crs;
  }

  public static GeojsonCrs epsg4326() {
    final GeojsonCrs crs = new GeojsonCrs();
    crs.setType("name");
    crs.setProperties(new GeojsonCrsProperties());
    crs.getProperties().setName(EPSG4326);

    return crs;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public GeojsonCrsProperties getProperties() {
    return properties;
  }

  public void setProperties(GeojsonCrsProperties properties) {
    this.properties = properties;
  }

}