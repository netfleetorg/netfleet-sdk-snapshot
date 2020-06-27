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

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.netfleet.sdk.util.Doubles.isGreaterEqual;
import static org.netfleet.sdk.util.Doubles.isSmallerEqual;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Interval {

  public final double left; // lower
  public final double right; // greater

  /**
   * @param left
   * @param right
   */
  public Interval(double left, double right) {
    this.left = min(left, right);
    this.right = max(left, right);
  }

  /**
   * @param interval
   */
  public Interval(Interval interval) {
    this.left = interval.left;
    this.right = interval.right;
  }

  /**
   * @param left
   * @param right
   * @return
   */
  public static Interval of(final double left, final double right) {
    return new Interval(left, right);
  }

  /**
   * @param l0
   * @param r0
   * @param l1
   * @param r1
   * @return true if {l0, r0} contains {l1, r1}. Otherwise false.
   */
  public static final boolean contains(double l0, double r0, double l1, double r1) {
    return _contains(l0, r0, l1, r1);
  }

  /**
   * @param left
   * @param right
   * @param value
   * @return true if value in range between {left, right}
   */
  public static final boolean inRange(double left, double right, double value) {
    return _inRange(left, right, value);
  }

  /**
   * @param l0
   * @param r0
   * @param l1
   * @param r1
   * @return true if {l0, r0} intersects with {l1, r1}. Otherwise false.
   */
  public static final boolean intersects(double l0, double r0, double l1, double r1) {
    return _intersects(l0, r0, l1, r1);
  }

  /**
   * @param l0
   * @param r0
   * @param l1
   * @param r1
   * @return intersection Interval between {l0, l1} and {r0, r1}.
   */
  public static final Interval intersection(double l0, double r0, double l1, double r1) {
    return _intersection(l0, r0, l1, r1);
  }

  /**
   * @param l0
   * @param r0
   * @param l1
   * @param r1
   * @return
   */
  private static boolean _contains(double l0, double r0, double l1, double r1) {
    return isGreaterEqual(l1, l0) && isSmallerEqual(r1, r0);
  }

  /**
   * @param double
   * @param double
   * @return true if W in range between left and right. Otherwise false.
   */
  private static boolean _inRange(double left, double right, double W) {
    return isGreaterEqual(W, left) && isSmallerEqual(W, right);
  }

  /**
   * @param double
   * @param double
   * @param double
   * @param double
   * @return true if {l0, r0} intersects with {l1, r1}. Otherwise false.
   */
  private static boolean _intersects(double l0, double r0, double l1, double r1) {
    return isSmallerEqual(max(l0, l1), min(r0, r1));
  }

  /**
   * @param double
   * @param double
   * @param double
   * @param double
   * @return
   */
  private static Interval _intersection(double Ql, double Qr, double Wl, double Wr) {
    final double newLeft = max(Ql, Wl);
    final double newRight = min(Qr, Wr);

    return new Interval(newLeft, newRight);
  }

  /**
   * @param interval
   * @return if left and right variables is in the any contain line it returns true , otherwise it
   * returns false.
   */
  public boolean contains(Interval interval) {
    return _contains(left, right, interval.getLeft(), interval.getRight());
  }

  /**
   * @param left
   * @param right
   * @return true if the Interval contains {left, right}. Otherwise false.
   */
  public boolean contains(double left, double right) {
    return _contains(left, right, min(left, right), max(left, right));
  }

  /**
   * @param value
   * @return true if value in range of the Interval. Otherwise false.
   */
  public boolean inRange(double value) {
    return _inRange(left, right, value);
  }

  /**
   * @param interval
   * @return true if the Interval intersects with given Interval. Otherwise false.
   */
  public boolean intersects(Interval interval) {
    return _intersects(left, right, interval.getLeft(), interval.getRight());
  }

  /**
   * @param left
   * @param right
   * @return true if the Interval intersects with given {left, right}. Otherwise false.
   */
  public boolean intersects(double left, double right) {
    return _intersects(left, right, min(left, right), max(left, right));
  }

  /**
   * @param interval
   * @return intersection between the Interval and given Interval.
   */
  public Interval intersection(Interval interval) {
    return _intersection(left, right, interval.getLeft(), interval.getRight());
  }

  /**
   * @param left
   * @param right
   * @return intersection between the Interval and given {left, right}.
   */
  public Interval intersection(double left, double right) {
    return _intersection(left, right, min(left, right), max(left, right));
  }

  /**
   * @return left variable
   */
  public double getLeft() {
    return left;
  }

  /**
   * @return right variable
   */
  public double getRight() {
    return right;
  }

  /**
   * @return Finding middle of the line.
   */
  public double getMiddle() {
    return (left + right) * 0.5;
  }

  /**
   * @return Finding length of the line.
   */
  public double getLength() {
    return right - left;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 59 * hash + (int) (Double.doubleToLongBits(this.left) ^ (Double.doubleToLongBits(this.left) >>> 32));
    hash = 59 * hash + (int) (Double.doubleToLongBits(this.right) ^ (Double.doubleToLongBits(this.right) >>> 32));
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
    final Interval other = (Interval) obj;
    if (Double.doubleToLongBits(this.left) != Double.doubleToLongBits(other.left)) {
      return false;
    }

    return Double.doubleToLongBits(this.right) == Double.doubleToLongBits(other.right);
  }
}
