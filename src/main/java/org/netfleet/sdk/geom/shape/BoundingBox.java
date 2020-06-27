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

import org.netfleet.sdk.commons.math.Interval;
import org.netfleet.sdk.geom.*;
import org.netfleet.sdk.geom.crs.CoordinateReferenceSystem;
import org.netfleet.sdk.geom.crs.CoordinateSystemAxes;
import org.netfleet.sdk.util.uuid.DefaultUUIDGenerator;

import java.util.ArrayList;
import java.util.List;

public class BoundingBox extends AbstractGeometry {

  public Position southWest;
  public Position northEast;

  public BoundingBox(String id, CoordinateReferenceSystem crs,
                     Position southWest, Position northEast) {
    super(id, GeometryType.BOUNDING_BOX, crs);

    Position[] fixedBounds = _fixBounds(southWest, northEast);
    this.southWest = fixedBounds[0];
    this.northEast = fixedBounds[1];
  }

  public BoundingBox(Position southWest, Position northEast) {
    this(new DefaultUUIDGenerator().generate(), southWest, northEast);
  }

  public BoundingBox(String id, Position southWest, Position northEast) {
    super(id, GeometryType.BOUNDING_BOX);

    Position[] fixedBounds = _fixBounds(southWest, northEast);
    this.southWest = fixedBounds[0];
    this.northEast = fixedBounds[1];
  }

  private static Position[] _fixBounds(Position sw, Position ne) {
    int latOrder = CoordinateSystemAxes.GEODETIC_LATITUDE.getDirection().getOrder();
    int lonOrder = CoordinateSystemAxes.GEODETIC_LONGITUDE.getDirection().getOrder();

    double swLat = sw.getOrdinate(latOrder);
    double swLon = sw.getOrdinate(lonOrder);
    double neLat = ne.getOrdinate(latOrder);
    double neLon = ne.getOrdinate(lonOrder);

    final Interval intValX = new Interval(swLat, neLat);
    final Interval intValY = new Interval(swLon, neLon);

    Position p0 = new SimplePosition(2);
    p0.setOrdinate(latOrder, intValX.left);
    p0.setOrdinate(lonOrder, intValY.left);

    Position p1 = new SimplePosition(2);
    p1.setOrdinate(latOrder, intValX.right);
    p1.setOrdinate(lonOrder, intValY.right);

    return new Position[]{p0, p1};
  }

  public Position getSouthWest() {
    return southWest;
  }

  public Position getNorthEast() {
    return northEast;
  }

  @Override
  public Geometry clone() {
    return new BoundingBox(getId(), getCoordinateReferenceSystem(), getSouthWest(), getNorthEast());
  }

  @Override
  public List<? extends Position> getBounds() {
    List<Position> positions = new ArrayList<>();
    positions.add(getSouthWest());
    positions.add(getNorthEast());

    return positions;
  }

  @Override
  public List<? extends Position> getPositions() {
    List<Position> positions = new ArrayList<>();
    positions.add(getSouthWest());
    positions.add(getNorthEast());

    return positions;
  }
}
