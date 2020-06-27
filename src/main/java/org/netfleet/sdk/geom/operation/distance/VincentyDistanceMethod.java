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
import org.netfleet.sdk.geom.operation.Formula;
import org.netfleet.sdk.geom.operation.OperationMethod;

import static java.lang.Math.*;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class VincentyDistanceMethod implements OperationMethod<Double> {

  private Double result = Double.NaN;

  private double srcLat, srcLon;
  private double tarLat, tarLon;

  public VincentyDistanceMethod(double srcLat, double srcLon,
                                double tarLat, double tarLon) {
    this.srcLat = srcLat;
    this.srcLon = srcLon;
    this.tarLat = tarLat;
    this.tarLon = tarLon;
  }

  @Reference(
      title = "Vincenty Formula",
      link = "https://en.wikipedia.org/wiki/Vincenty%27s_formulae"
  )
  public static double vincentyFromRadian(double lat1, double lon1, double lat2, double lon2) {
    double a = 6378137, b = 6356752.314245, f = 1 / 298.257223563;
    double L = lon2 - lon1;
    double U1 = atan((1 - f) * tan(lat1));
    double U2 = atan((1 - f) * tan(lat2));
    double sinU1 = sin(U1), cosU1 = cos(U1);
    double sinU2 = sin(U2), cosU2 = cos(U2);

    double sinLambda, cosLambda, sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;
    double lambda = L, lambdaP, iterLimit = 100;
    do {
      sinLambda = sin(lambda);
      cosLambda = cos(lambda);
      sinSigma = sqrt((cosU2 * sinLambda) * (cosU2 * sinLambda)
          + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
      if (sinSigma == 0) {
        return 0;
      }
      cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
      sigma = atan2(sinSigma, cosSigma);
      sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
      cosSqAlpha = 1 - sinAlpha * sinAlpha;
      cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
      if (Double.isNaN(cos2SigmaM)) {
        cos2SigmaM = 0;
      }
      double C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
      lambdaP = lambda;
      lambda = L + (1 - C) * f * sinAlpha
          * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
    } while ( abs(lambda - lambdaP) > 1e-12 && --iterLimit > 0 );

    if (iterLimit == 0) {
      return Double.NaN;
    }
    double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
    double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
    double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
    double deltaSigma = B
        * sinSigma
        * (cos2SigmaM + B
        / 4
        * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM
        * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));

    return b * A * (sigma - deltaSigma);
  }

  @Override
  public void execute() {
    result = vincentyFromRadian(srcLat, srcLon, tarLat, tarLon);
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
