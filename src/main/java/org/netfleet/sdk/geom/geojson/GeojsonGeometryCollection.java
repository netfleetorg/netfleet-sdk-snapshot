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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class GeojsonGeometryCollection extends GeojsonObject implements Iterable<GeojsonGeometry> {

  private GeojsonObjectType type = GeojsonObjectType.GeometryCollection;
  private List<GeojsonGeometry> geometries = new ArrayList<>();

  public GeojsonGeometryCollection() {
  }

  public GeojsonGeometryCollection(List<GeojsonGeometry> geometries) {
    this.geometries = geometries;
  }

  public boolean addGeometry(GeojsonGeometry geometry) {
    return getGeometries().add(geometry);
  }

  public void setGeometries(List<GeojsonGeometry> geometries) {
    this.geometries = geometries;
  }

  public List<GeojsonGeometry> getGeometries() {
    if (this.geometries == null) {
      setGeometries(new ArrayList<GeojsonGeometry>());
    }

    return geometries;
  }

  @Override
  public <T> T accept(GeojsonObjectVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
  public Iterator<GeojsonGeometry> iterator() {
    return getGeometries().iterator();
  }
}
