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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class Polyline extends AbstractGeometry {

  private List<Position> list;

  public Polyline(String id, CoordinateReferenceSystem crs, List<Position> list) {
    super(id, GeometryType.POLYLINE, crs);
    this.list = list;
  }

  public Polyline(String id, List<Position> list) {
    super(id, GeometryType.POLYLINE);
    this.list = list;
  }

  @Override
  public Geometry clone() {
    return new Polyline(getId(), getCoordinateReferenceSystem(), list);
  }

  public Collection<Segment> toSegments() {
    Collection<Segment> segments = new LinkedList<>();

    final int len = list.size();
    for ( int i = 0; i < len; i++ ) {
      // index of segment's target
      final int next = i + 1;

      // avoid OutOfIndex
      if (next < len) {

        // init source point of segment
        Position currentSrc = list.get(i);

        // init target point of segment
        Position currentTar = list.get(i + 1);

        // push to the list
        segments.add(new Segment(currentSrc, currentTar));
      }
    }

    return segments;
  }

  @Override
  public List<? extends Position> getBounds() {
    return null;
  }

  @Override
  public List<? extends Position> getPositions() {
    return list;
  }
}
