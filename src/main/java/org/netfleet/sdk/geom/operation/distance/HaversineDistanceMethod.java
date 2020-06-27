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

package org.netfleet.sdk.geom.operation.distance;

import org.netfleet.sdk.annotation.Reference;
import org.netfleet.sdk.geom.GeoUtil;
import org.netfleet.sdk.geom.operation.Formula;
import org.netfleet.sdk.geom.operation.OperationMethod;

import static java.lang.Math.*;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class HaversineDistanceMethod implements OperationMethod<Double> {

  private Double result = Double.NaN;

  private double srcLat, srcLon;
  private double tarLat, tarLon;

  public HaversineDistanceMethod(double srcLat, double srcLon,
                                 double tarLat, double tarLon) {
    this.srcLat = srcLat;
    this.srcLon = srcLon;
    this.tarLat = tarLat;
    this.tarLon = tarLon;
  }

  @Reference(
      title = "Haversine Formula",
      link = "https://en.wikipedia.org/wiki/Haversine_formula"
  )
  public static double haversineFromRadian(double lat0, double lon0,
                                           double lat1, double lon1) {
    final double _letX = lat1 - lat0;
    final double lat = sin(0.5 * _letX);

    final double _letY = lon1 - lon0;
    final double lon = sin(0.5 * _letY);

    double _let0 = (lon * lon) * cos(lat0) * cos(lat1);
    _let0 += (lat * lat);

    double _let1 = atan2(sqrt(_let0), sqrt(1 - _let0));
    _let1 *= 2;

    return _let1 * GeoUtil.EARTH_RADIUS_M;
  }

  @Override
  public void execute() {
    result = haversineFromRadian(getSrcLat(), getSrcLon(), getTarLat(), getTarLon());
  }

  @Override
  public Double getResult() {
    return result;
  }

  @Override
  public Formula getFormula() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Integer getSourceDimension() {
    return 2;
  }

  @Override
  public Integer getTargetDimension() {
    return 2;
  }

  public double getSrcLat() {
    return srcLat;
  }

  public double getSrcLon() {
    return srcLon;
  }

  public double getTarLat() {
    return tarLat;
  }

  public double getTarLon() {
    return tarLon;
  }
}
