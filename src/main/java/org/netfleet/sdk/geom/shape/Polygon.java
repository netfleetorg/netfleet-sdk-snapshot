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
import org.netfleet.sdk.util.Ints;
import org.netfleet.sdk.util.uuid.DefaultUUIDGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class Polygon extends AbstractGeometry {

  private List<Position> vertices;

  public Polygon(List<Position> vertices) {
    super(new DefaultUUIDGenerator().generate(), GeometryType.POLYGON);
    this.vertices = vertices;
  }

  public Polygon(String id, CoordinateReferenceSystem crs, List<Position> vertices) {
    super(id, GeometryType.POLYGON, crs);
    this.vertices = vertices;
  }

  public Polygon(String id, List<Position> vertices) {
    super(id, GeometryType.POLYGON);
    this.vertices = vertices;
  }

  public boolean isClosed() {
    final List<Position> _lst = this.vertices;
    final Position _org = _lst.get(0);
    final int _lh = _lst.size() - 1;

    return _lst.get(_lh).equals(_org);
  }

  public Collection<Segment> toSegments() {
    final Collection<Segment> _sgm = new ArrayList<>();
    final List<Position> _vx = this.vertices;

    boolean _clsd = isClosed();
    int _lh = _vx.size();

    for ( int i = 0; i < _lh; i++ ) {

      final int _idx = i + 1;

      if (Ints.isSmaller(_idx, _lh)) {

        final Position _src = _vx.get(i);
        final Position _tar = _vx.get(_idx);
        final Segment _seg = new Segment(_src, _tar);

        _sgm.add(_seg);

      }

    }

    // return segments as closed-polygon
    if (!_clsd) {

      final Position _src = _vx.get(_lh - 1);
      final Position _tar = _vx.get(0);
      final Segment _seg = new Segment(_src, _tar);
      _sgm.add(_seg);

    }

    return _sgm;
  }

  public int getVerticesCount() {
    return vertices.size();
  }

  @Override
  public Geometry clone() {
    return new Polygon(getId(), getCoordinateReferenceSystem(), vertices);
  }

  @Override
  public List<? extends Position> getBounds() {
    return null;
  }

  @Override
  public List<? extends Position> getPositions() {
    return vertices;
  }
}
