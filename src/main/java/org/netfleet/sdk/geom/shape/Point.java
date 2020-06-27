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

package org.netfleet.sdk.geom.shape;

import org.netfleet.sdk.geom.AbstractGeometry;
import org.netfleet.sdk.geom.Geometry;
import org.netfleet.sdk.geom.GeometryType;
import org.netfleet.sdk.geom.Position;
import org.netfleet.sdk.geom.crs.CoordinateReferenceSystem;
import org.netfleet.sdk.util.uuid.DefaultUUIDGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class Point extends AbstractGeometry {

  private Position coordinate;

  public Point(String id, CoordinateReferenceSystem crs, Position coordinate) {
    super(id, GeometryType.POINT, crs);
    this.coordinate = coordinate;
  }

  public Point(String id, Position coordinate) {
    super(id, GeometryType.POINT);
    this.coordinate = coordinate;
  }

  public Point(Position coordinate) {
    this(new DefaultUUIDGenerator().generate(), coordinate);
  }

  public Position getCoordinate() {
    return coordinate;
  }

  @Override
  public Geometry clone() {
    return new Point(getId(), getCoordinateReferenceSystem(), getCoordinate());
  }

  @Override
  public List<? extends Position> getBounds() {
    List<Position> positions = new ArrayList<>();
    positions.add(coordinate);

    return positions;
  }

  @Override
  public List<? extends Position> getPositions() {
    List<Position> positions = new ArrayList<>();
    positions.add(coordinate);

    return positions;
  }
}
