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

import org.junit.Ignore;
import org.netfleet.sdk.geom.operation.OperationMethod;

public class DistanceOperationTest {

  @Ignore
  public void testSimple() {
    double srcLat = Math.toRadians(37.232);
    double srcLon = Math.toRadians(32.87232);
    double tarLat = Math.toRadians(37.132);
    double tarLon = Math.toRadians(32.97232);

    DistanceOperation operation =
        new DistanceOperation(DistanceType.HAVERSINE, srcLat, srcLon, tarLat, tarLon);

    OperationMethod<Double> method = operation.getOperationMethod();
    method.execute();
    double result = method.getResult();

    System.out.println(result);
  }

}
