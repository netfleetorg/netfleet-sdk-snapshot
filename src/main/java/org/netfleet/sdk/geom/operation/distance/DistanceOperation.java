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


import org.netfleet.sdk.geom.crs.CoordinateReferenceSystem;
import org.netfleet.sdk.geom.crs.GeographicCoordinateReferenceSystem;
import org.netfleet.sdk.geom.operation.Operation;
import org.netfleet.sdk.geom.operation.OperationMethod;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class DistanceOperation implements Operation<Double> {

  private DistanceType distanceType;

  private double srcLat, srcLon;
  private double tarLat, tarLon;

  public DistanceOperation(DistanceType distanceType,
                           double srcLat, double srcLon,
                           double tarLat, double tarLon) {
    this.distanceType = distanceType;
    this.srcLat = srcLat;
    this.srcLon = srcLon;
    this.tarLat = tarLat;
    this.tarLon = tarLon;
  }

  @Override
  public CoordinateReferenceSystem getSourceCRS() {
    return GeographicCoordinateReferenceSystem.WGS84;
  }

  @Override
  public CoordinateReferenceSystem getTargetCRS() {
    return GeographicCoordinateReferenceSystem.WGS84;
  }

  @Override
  public OperationMethod<Double> getOperationMethod() {
    switch (distanceType) {
      case HAVERSINE:
        return new HaversineDistanceMethod(srcLat, srcLon, tarLat, tarLon);
      case VINCENTY:
        return new VincentyDistanceMethod(srcLat, srcLon, tarLat, tarLon);
      default:
        throw new IllegalArgumentException("Unknown distance type.");
    }
  }
}
