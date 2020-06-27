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
import org.netfleet.sdk.util.DistanceNode;
import org.netfleet.sdk.util.uuid.DefaultUUIDGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class Circle extends AbstractGeometry {

  public Position center;
  public DistanceNode radius;

  public Circle(String id, CoordinateReferenceSystem crs,
                Position center, DistanceNode radius) {
    super(id, GeometryType.CIRCLE, crs);
    this.center = center;
    this.radius = radius;
  }

  public Circle(String id, Position center, DistanceNode radius) {
    super(id, GeometryType.CIRCLE);
    this.center = center;
    this.radius = radius;
  }

  public Circle(Position center, DistanceNode radius) {
    this(new DefaultUUIDGenerator().generate(), center, radius);
  }

  public Position getCenter() {
    return center;
  }

  public DistanceNode getRadius() {
    return radius;
  }

  @Override
  public Geometry clone() {
    return new Circle(getId(), getCoordinateReferenceSystem(), getCenter(), getRadius());
  }

  @Override
  public List<? extends Position> getBounds() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<? extends Position> getPositions() {
    List<Position> positions = new ArrayList<>();
    positions.add(center);

    return positions;
  }
}
