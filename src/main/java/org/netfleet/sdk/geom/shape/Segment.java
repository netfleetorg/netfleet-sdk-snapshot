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
import org.netfleet.sdk.geom.crs.GeographicCoordinateReferenceSystem;
import org.netfleet.sdk.util.uuid.DefaultUUIDGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class Segment extends AbstractGeometry {

  private Position source;
  private Position target;

  public Segment(String id, CoordinateReferenceSystem crs,
                 Position source, Position target) {
    super(id, GeometryType.SEGMENT, crs);
    this.source = source;
    this.target = target;
  }

  public Segment(Position source, Position target) {
    this(new DefaultUUIDGenerator().generate(), source, target);
  }

  public Segment(String id, Position source, Position target) {
    super(id, GeometryType.SEGMENT);

    this.source = source;
    this.target = target;
  }

  public Position getSource() {
    return source;
  }

  public Position getTarget() {
    return target;
  }

  @Override
  public Geometry clone() {
    return new Segment(getId(), getSource(), getTarget());
  }

  @Override
  public List<? extends Position> getBounds() {
    int latOrder = CoordinateSystemAxes.GEODETIC_LATITUDE.getDirection().getOrder();
    int lonOrder = CoordinateSystemAxes.GEODETIC_LONGITUDE.getDirection().getOrder();

    double srcLat = source.getOrdinate(latOrder);
    double srcLon = source.getOrdinate(lonOrder);
    double tarLat = target.getOrdinate(latOrder);
    double tarLon = target.getOrdinate(lonOrder);

    Interval intValX = new Interval(srcLat, tarLat);
    Interval intValY = new Interval(srcLon, tarLon);

    Position p0 = new SimplePosition(GeographicCoordinateReferenceSystem.WGS84, 2);
    p0.setOrdinate(latOrder, intValX.left);
    p0.setOrdinate(lonOrder, intValY.left);

    Position p1 = new SimplePosition(GeographicCoordinateReferenceSystem.WGS84, 2);
    p1.setOrdinate(latOrder, intValX.right);
    p1.setOrdinate(lonOrder, intValY.right);

    return Arrays.asList(p0, p1);
  }

  @Override
  public List<? extends Position> getPositions() {
    List<Position> positions = new ArrayList<>();
    positions.add(getSource());
    positions.add(getTarget());

    return positions;
  }

}
