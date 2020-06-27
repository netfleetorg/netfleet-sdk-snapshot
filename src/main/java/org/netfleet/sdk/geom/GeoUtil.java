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

package org.netfleet.sdk.geom;

import org.netfleet.sdk.commons.math.Angle;
import static java.lang.Math.*;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public final class GeoUtil {

  public static final double EARTH_DIAMETER = Double.parseDouble("12756.274");
  public static final double EARTH_RADIUS_KM = 6371.0;
  public static final double EARTH_RADIUS_M = 6371000.0;

  private GeoUtil() {
  }

  public static double initialBearingFromRadian(double lat0, double lon0,
                                                double lat1, double lon1) {
    final double _lat0 = lat0;
    final double _lat1 = lat1;

    final double _dlon = lon1 - lon0;

    final double _letY = sin(_dlon) * cos(_lat1);
    final double _letX = cos(_lat0) * sin(_lat1) - sin(_lat0) * cos(_lat1) * cos(_dlon);

    return toDegrees(atan2(_letY, _letX));
  }

  public static Angle bearingCCW(double lat0, double lon0,
                                 double lat1, double lon1) {
    return Angle.fromDegree((360 - ((360 + initialBearingFromRadian(lat0, lon0, lat1, lon1)))) % 360);
  }

}
