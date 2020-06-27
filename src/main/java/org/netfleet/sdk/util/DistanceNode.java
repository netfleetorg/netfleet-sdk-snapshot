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
package org.netfleet.sdk.util;

import org.netfleet.sdk.commons.unit.DistanceUnit;

import java.io.Serializable;

public final class DistanceNode implements Serializable {

  public final DistanceUnit unit;
  public final double distance;

  public DistanceNode(DistanceUnit unit, double distance) {
    this.unit = unit;
    this.distance = distance;
  }

  public static DistanceNode of(DistanceUnit unit, double distance) {
    return new DistanceNode(unit, distance);
  }

  public static DistanceNode fromMeter(double distance) {
    return new DistanceNode(DistanceUnit.METER, distance);
  }

  public static DistanceNode fromKilometer(double distance) {
    return new DistanceNode(DistanceUnit.KILOMETER, distance);
  }

  public double asMeter() {
    if (unit.equals(DistanceUnit.METER)) {
      return distance;
    }

    return DistanceUnit.KILOMETER.toMeter(distance);
  }

  public double asKilometer() {
    if (unit.equals(DistanceUnit.KILOMETER)) {
      return distance;
    }

    return DistanceUnit.METER.toKilometer(distance);
  }

  public DistanceUnit getUnit() {
    // herkes delirmiş.
    return unit;
  }

  public double getDistance() {
    return distance;
  }
}