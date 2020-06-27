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

import static java.lang.Math.abs;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static org.netfleet.sdk.util.Doubles.isEqual;
import static org.netfleet.sdk.util.Doubles.isGreaterEqual;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @author Yakup Zengin - yzengin [at] terrayazilim [dot] com [dot] tr
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Vec2d implements Cloneable {

  public final double x;
  public final double y;

  /**
   * @param x
   * @param y
   */
  public Vec2d(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * @param vector
   */
  public Vec2d(Vec2d vector) {
    this.x = vector.x;
    this.y = vector.y;
  }

  /**
   * @param x
   * @param y
   * @return
   */
  public static final Vec2d of(final double x, final double y) {
    return new Vec2d(x, y);
  }

  /**
   * @param vector
   * @return
   */
  public static final Vec2d of(Vec2d vector) {
    return new Vec2d(vector);
  }

  /**
   * @param Vec2d
   * @param double
   * @param double
   * @return
   */
  private static Vec2d _add(Vec2d vector, double nx, double ny) {
    return new Vec2d(vector.getX() + nx, vector.getY() + ny);
  }

  /**
   * @param Vec2d
   * @param double
   * @param double
   * @return
   */
  private static Vec2d _sub(Vec2d vector, double nx, double ny) {
    return new Vec2d(vector.getX() - nx, vector.getY() - ny);
  }

  /**
   * Formula
   * <p>
   * Q = x0 + x1 W = y0 + y1
   *
   * @param vector
   * @return
   */
  public Vec2d add(Vec2d vector) {
    return _add(this, vector.getX(), vector.getY());
  }

  /**
   * Formula
   * <p>
   * Q = x0 - x1 W = y0 - y1
   *
   * @param vector
   * @return
   */
  public Vec2d sub(Vec2d vector) {
    return _sub(this, vector.getX(), vector.getY());
  }

  /**
   * Formula
   * <p>
   * Q = x0 + t W = y0 + t
   *
   * @param t
   * @return
   */
  public Vec2d translate(double t) {
    return _add(this, t, t);
  }

  /**
   * Formula
   * <p>
   * Q = x1 * t W = y1 * t
   *
   * @param t
   * @return
   */
  public Vec2d scale(double t) {
    return new Vec2d(x * t, y * t);
  }

  /**
   * Formula
   * <p>
   * Q = |x1| W = |y1|
   *
   * @return
   */
  public Vec2d absoluted() {
    return new Vec2d(abs(x), abs(y));
  }

  /**
   * @param q
   * @return
   */
  private static double _negate(double q) {
    return isGreaterEqual(q, 0.0) ? -1 * q : q;
  }

  /**
   * @return
   */
  public Vec2d negated() {
    return new Vec2d(_negate(x), _negate(y));
  }

  /**
   * @return
   */
  public Vec2d rounded() {
    return new Vec2d(round(x), round(y));
  }

  /**
   * Formula:
   * <p>
   * dx = x1 * x0 dy = y1 * y0 t = dx + dy
   *
   * @param vector arg
   * @return calculates dot product with given argument Vec2d
   */
  public double getDotProduct(Vec2d vector) {
    double dx = vector.getX() * x;
    double dy = vector.getY() * y;

    return dx + dy;
  }

  /**
   * Formula
   * <p>
   * Q1 = x1 - x0 W1 = y1 - y0 Q2 = x2 - x0 W2 = y2 - y0 t = (Q1 * W1) - (Q2 * W2)
   *
   * @param vector0
   * @param vector1
   * @return
   */
  public double getCrossProduct(Vec2d vector0, Vec2d vector1) {
    // final double x0 = this.x;
    // final double y0 = this.y;
    // final double x1 = vector0.getX();
    // final double y1 = vector0.getY();
    // final double x2 = vector1.getX();
    // final double y2 = vector1.getY();
    // final double leg1_step1 = x1 - x0;
    // final double leg1_step2 = y1 - y0;
    // final double leg2_step1 = x2 - x0;
    // final double leg2_step2 = y2 - y0;
    // return (leg1_step1 * leg2_step2) - (leg1_step2 * leg2_step1);

    final Vec2d Qv = vector0.sub(this);
    final Vec2d Wv = vector1.sub(this);

    return (Qv.getX() * Wv.getY()) - (Qv.getY() * Wv.getX());
  }

  /**
   * Formula
   * <p>
   * Q = |x1| W = |y1| t = Q + W
   *
   * @return
   */
  public double getManhattanNorm() {
    // final double dx = Math.abs(this.x);
    // final double dy = Math.abs(this.y);
    // return dx + dy;

    final Vec2d vector = absoluted();

    return vector.getX() + vector.getY();
  }

  /**
   * Formula
   * <p>
   * Q = math.sqrt((x0 * x0 ) + (y0 * y0))
   *
   * @return
   */
  public double getNorm() {
    return sqrt(getNormSquared());
  }

  /**
   * Formula:
   * <p>
   * Q = x0 * x0 W = y0 * y0 t = Q + W
   *
   * @return
   */
  public double getNormSquared() {
    // final double dx = x * x;
    // final double dy = y * y;
    // return dx + dy;

    return getDotProduct(this);
  }

  /**
   * Formula
   * <p>
   * Normal = getNorm = math.sqrt(x1,y1) Q = x0 * (1 / math.sqrt(x1,y1)) W = y0 * (1 /
   * math.sqrt(x1,y1))
   *
   * @return
   */
  public Vec2d getNormalized() {
    final double normal = getNorm();
    if (isEqual(normal, 0d)) {
      throw new IllegalStateException("1 / 0 is unknown");
    }

    // double dx = this.x * (1 / normal);
    // double dy = this.y * (1 / normal);
    // return new Vec2d(dx, dy);
    return new Vec2d(scale(1 / normal));
  }

  /**
   * Formula:
   * <p>
   * Q = x1 - x0 W = y1 - y0 t = (Q * Q) + (W * W)
   *
   * @param vector
   * @return
   */
  public double distanceSquaredTo(Vec2d vector) {
    // final double dx = vector.getX() - this.x;
    // final double dy = vector.getY() - this.y;
    // return (dx * dx) + (dy * dy);

    final Vec2d d = vector.sub(this);

    return d.getNormSquared();
  }

  /**
   * Formula:
   * <p>
   * Q = x1 - x0 W = y1 - y0 return t = math.sqrt((Q * Q) + (W * W))
   *
   * @param vector
   * @return
   */
  public double distanceTo(Vec2d vector) {
    return sqrt(distanceSquaredTo(vector));
  }

  /**
   * @return
   */
  public Vec2d getZero() {
    return new Vec2d(0d, 0d);
  }

  /**
   * @return
   */
  public double[] toArray() {
    return new double[]{x, y};
  }

  /**
   * @return
   */
  public double getX() {
    return x;
  }

  /**
   * @return
   */
  public double getY() {
    return y;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 29 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    hash = 29 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
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
    final Vec2d other = (Vec2d) obj;
    if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
      return false;
    }
    if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
      return false;
    }
    return true;
  }
}
