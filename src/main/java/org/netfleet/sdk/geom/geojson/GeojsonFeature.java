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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class GeojsonFeature extends GeojsonObject {

  private GeojsonObjectType type = GeojsonObjectType.Feature;

  private Map<String, Object> properties = new LinkedHashMap<>();
  private GeojsonGeometry geometry;
  private String id;

  public GeojsonFeature() {
  }

  public GeojsonFeature(GeojsonGeometry geometry) {
    this.geometry = geometry;
  }

  public GeojsonFeature(GeojsonGeometry geometry, String id) {
    this.geometry = geometry;
    this.id = id;
  }

  public GeojsonFeature(Map<String, Object> properties, GeojsonGeometry geometry, String id) {
    this.properties = properties;
    this.geometry = geometry;
    this.id = id;
  }

  public <T> T setProperty(String key, Object value) {
    return (T) getProperties().put(key, value);
  }

  public <T> T getProperty(String key) {
    return (T) getProperties().get(key);
  }

  @Override
  public GeojsonObjectType getType() {
    return type;
  }

  @Override
  public void setType(GeojsonObjectType type) {
    this.type = type;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  public GeojsonGeometry getGeometry() {
    return geometry;
  }

  public void setGeometry(GeojsonGeometry geometry) {
    this.geometry = geometry;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public <T> T accept(GeojsonObjectVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
