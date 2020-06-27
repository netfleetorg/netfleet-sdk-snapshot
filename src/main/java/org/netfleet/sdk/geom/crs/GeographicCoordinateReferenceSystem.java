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

package org.netfleet.sdk.geom.crs;

public class GeographicCoordinateReferenceSystem extends AbstractCoordinateReferenceSystem {

  public static final GeographicCoordinateReferenceSystem WGS84
      = new GeographicCoordinateReferenceSystem(CrsIdentifier.EPSG4326,
      "WGS84",
      EllipsoidalCoordinateSystem2D.GEODETIC_2D,
      Datum.WGS84,
      PrimeMeridian.GREENWICH);

  private Datum datum;
  private PrimeMeridian primeMeridian;

  public GeographicCoordinateReferenceSystem(String name, CoordinateSystem coordinateSystem,
                                             Datum datum, PrimeMeridian primeMeridian) {
    this(CrsIdentifier.EPSG4326, name, coordinateSystem, datum, primeMeridian);
  }

  public GeographicCoordinateReferenceSystem(CrsIdentifier crsId, String name,
                                             CoordinateSystem coordinateSystem, Datum datum,
                                             PrimeMeridian primeMeridian) {
    super(crsId, name, coordinateSystem);
    this.datum = datum;
    this.primeMeridian = primeMeridian;
  }

  public Datum getDatum() {
    return datum;
  }

  public PrimeMeridian getPrimeMeridian() {
    return primeMeridian;
  }
}
