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

public class PrimeMeridian extends AbstractCrsIdentifiable {

  public static final PrimeMeridian GREENWICH
      = new PrimeMeridian(CrsIdentifier.EPSG4326, "Greenwich", 0d);

  private final double longitude;

  public PrimeMeridian(String name, double longitude) {
    this(CrsIdentifier.EPSG4326, name, longitude);
  }

  public PrimeMeridian(CrsIdentifier crsId, String name, double longitude) {
    super(crsId, name);
    this.longitude = longitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
