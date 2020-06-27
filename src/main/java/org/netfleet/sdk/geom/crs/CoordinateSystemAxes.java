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

import org.netfleet.sdk.commons.unit.Units;

public final class CoordinateSystemAxes {
  private CoordinateSystemAxes() {
  }

  public static final GeodeticLatitudeAxis GEODETIC_LATITUDE
      = new GeodeticLatitudeAxis("lat", Units.DEGREE);

  public static final GeodeticLongitudeAxis GEODETIC_LONGITUDE
      = new GeodeticLongitudeAxis("lon", Units.DEGREE);

  public static final DefaultCoordinateSystemAxis ELLIPSOIDAL_HEIGHT
      = new DefaultCoordinateSystemAxis("h", AxisDirection.UP,
      Units.METRE, 0.0d, Double.MAX_VALUE);

  public static final StraightLineAxis X
      = new StraightLineAxis("X",
      AxisDirection.EAST, Units.METRE);

  public static final StraightLineAxis Y
      = new StraightLineAxis("Y",
      AxisDirection.NORTH, Units.METRE);

  public static final StraightLineAxis Z
      = new StraightLineAxis("Z",
      AxisDirection.UP, Units.METRE);

}
