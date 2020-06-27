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
package org.netfleet.sdk.commons.math;

import static org.netfleet.sdk.util.Doubles.*;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Angle implements Comparable<Angle> {

  public final double degree;
  public final double radian;

  /**
   */
  private Angle(double degree) {
    this.degree = degree;
    this.radian = Math.toRadians(this.degree);
  }

  /**
   * @param angle
   */
  public Angle(Angle angle) {
    this.degree = angle.degree;
    this.radian = angle.radian;
  }

  /**
   * @param degree
   * @return
   */
  public static Angle fromDegree(final double degree) {
    return new Angle(degree);
  }

  /**
   * @param radian
   * @return
   */
  public static Angle fromRadian(final double radian) {
    return new Angle(Math.toDegrees(radian));
  }

  /**
   * @param angle
   * @return
   */
  public Angle add(Angle angle) {
    return Angle.fromDegree(this.degree + angle.degree);
  }

  /**
   * @param angle
   * @return
   */
  public Angle sub(Angle angle) {
    return Angle.fromDegree(this.degree - angle.degree);
  }

  /**
   * @param angle
   * @return
   */
  public Angle mul(Angle angle) {
    return Angle.fromDegree(this.degree * angle.degree);
  }

  /**
   * @param angle
   * @return
   */
  public Angle div(Angle angle) {
    return Angle.fromDegree(this.degree / angle.degree);
  }

  /**
   * @param angle
   * @return
   */
  public Angle min(Angle angle) {
    if (isSmallerEqual(compareTo(angle), 0d)) {
      return this;
    } else {
      return new Angle(angle);
    }
  }

  /**
   * @param angle
   * @return
   */
  public Angle max(Angle angle) {
    if (isGreaterEqual(compareTo(angle), 0d)) {
      return this;
    } else {
      return new Angle(angle);
    }
  }

  /**
   * @return
   */
  public double sin() {
    return Math.sin(this.radian);
  }

  /**
   * @return
   */
  public double asin() {
    return Math.asin(this.radian);
  }

  /**
   * @return
   */
  public double cos() {
    return Math.cos(this.radian);
  }

  /**
   * @return
   */
  public double acos() {
    return Math.acos(this.radian);
  }

  /**
   * @return
   */
  public double atan() {
    return Math.atan(this.radian);
  }

  /**
   * @return
   */
  public double getRadian() {
    return this.radian;
  }

  /**
   * @return
   */
  public double getDegree() {
    return this.degree;
  }

  @Override
  public int compareTo(Angle angle) {
    if (isSmaller(this.radian, angle.degree)) {
      return -1;
    } else if (isEqual(this.radian, angle.degree)) {
      return 0;
    } else {
      return 1;
    }
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 37 * hash + (int) (Double.doubleToLongBits(this.degree) ^ (Double.doubleToLongBits(this.degree) >>> 32));
    hash = 37 * hash + (int) (Double.doubleToLongBits(this.radian) ^ (Double.doubleToLongBits(this.radian) >>> 32));
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Angle other = (Angle) obj;
    if (Double.doubleToLongBits(this.degree) != Double.doubleToLongBits(other.degree)) {
      return false;
    }
    if (Double.doubleToLongBits(this.radian) != Double.doubleToLongBits(other.radian)) {
      return false;
    }
    return true;
  }
}
