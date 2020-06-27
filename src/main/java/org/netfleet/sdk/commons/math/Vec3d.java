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
import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static org.netfleet.sdk.util.Doubles.*;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @author Yakup Zengin - yzengin [at] terrayazilim [dot] com [dot] tr
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Vec3d implements Cloneable {

  public final double x;
  public final double y;
  public final double z;

  /**
   * @param x
   * @param y
   * @param z
   */
  public Vec3d(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * x = x1; y = y1; z = z1;
   *
   * @param vector
   */
  public Vec3d(Vec3d vector) {
    this.x = vector.getX();
    this.y = vector.getY();
    this.z = vector.getZ();
  }

  /**
   * @param x
   * @param y
   * @param z
   * @return
   */
  public static final Vec3d of(final double x, final double y, final double z) {
    return new Vec3d(x, y, z);
  }

  /**
   * @param vector
   * @return
   */
  public static final Vec3d of(Vec3d vector) {
    return new Vec3d(vector);
  }

  /**
   * @param Vec3d
   * @param double
   * @param double
   * @param double
   * @return
   */
  private static Vec3d _add(Vec3d vector, double nx, double ny, double nz) {
    return new Vec3d(vector.getX() + nx, vector.getY() + ny, vector.getZ() + nz);
  }

  /**
   * @param Vec3d
   * @param double
   * @param double
   * @param double
   * @return
   */
  private static Vec3d _sub(Vec3d vector, double nx, double ny, double nz) {
    return new Vec3d(vector.getX() - nx, vector.getY() - ny, vector.getZ() - nz);
  }

  /**
   * @param vector
   * @return
   */
  public Vec3d add(Vec3d vector) {
    return _add(this, vector.getX(), vector.getY(), vector.getZ());
  }

  /**
   * @param vector
   * @return
   */
  public Vec3d sub(Vec3d vector) {
    return _sub(this, vector.getX(), vector.getY(), vector.getZ());
  }

  /**
   * Formula
   * <p>
   * Q = x0 + t W = y0 + t T = z0 + t
   *
   * @param t
   * @return
   */
  public Vec3d translate(double t) {
    return _add(this, t, t, t);
  }

  /**
   * @param t
   * @return
   */
  public Vec3d scale(double t) {
    return new Vec3d(x * t, y * t, z * t);
  }

  /**
   * Formula
   * <p>
   * Q = |x| W = |y| T = |z|
   *
   * @return
   */
  public Vec3d absoluted() {
    return new Vec3d(abs(x), abs(y), abs(z));
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
  public Vec3d negated() {
    return new Vec3d(_negate(x), _negate(y), _negate(z));
  }

  /**
   * @return
   */
  public Vec3d rounded() {
    return new Vec3d(round(x), round(y), round(z));
  }

  /**
   * @return
   */
  public Angle getAlpha() {
    final double angle = atan2(y, x);

    return Angle.fromDegree(angle);
  }

  /**
   * @return
   */
  public Angle getDelta() {
    return Angle.fromDegree(asin(this.z / getNorm()));
  }

  /**
   * dx = x0 * x1; dy = y0 * y1; dz = z0 * z1; d = dx + dy + dz;
   *
   * @param vector
   * @return
   */
  public double getDotProduct(Vec3d vector) {
    final double dx = x * vector.getX();
    final double dy = y * vector.getY();
    final double dz = z * vector.getZ();

    return dx + dy + dz;
  }

  /**
   * Formula
   * <p>
   * dx = y0 * z1 - z0 * y1; dy = z0 * x1 - x0 * z1; dz = x0 * y1 - y0 * x1;
   *
   * @param vector
   * @return
   */
  public Vec3d getCrossProduct(Vec3d vector) {
    final double dx = y * vector.getZ() - z * vector.getY();
    final double dy = z * vector.getX() - x * vector.getZ();
    final double dz = x * vector.getY() - y * vector.getX();

    return new Vec3d(dx, dy, dz);
  }

  /**
   * Formula
   * <p>
   * Q = |x0| W = |y0| T = |z0| v = Q + W + T
   *
   * @return
   */
  public double getManhattanNorm() {
    // final double dx = Math.abs(this.x);
    // final double dy = Math.abs(this.y);
    // final double dz = Math.abs(this.z);
    // return dx + dy + dz;

    final Vec3d vector = absoluted();
    return vector.getX() + vector.getY() + vector.getZ();
  }

  /**
   * Formula
   * <p>
   * Q = (x0 * x0) + (y0 * y0) + (z0 * z0)
   *
   * @return
   */
  public double getNormSquared() {
    // final double dx = this.x * this.x;
    // final double dy = this.y * this.y;
    // final double dz = this.z * this.z;
    // return dx + dy + dz;

    return getDotProduct(this);
  }

  /**
   * Formula
   * <p>
   * Q = math.sqrt((x0 * x0) + (y0 * y0) + (z0 * z0))
   *
   * @return
   */
  public double getNorm() {
    return sqrt(getNormSquared());
  }

  /**
   * Formula:
   * <p>
   * C = math.sqrt((x0 * x0) + (y0 * y0) + (z0 * z0)) Normal = getNorm = C Q = x0 * (1 / C) W = y0 *
   * (1 / C) T = z0 * (1 / C)
   *
   * @return
   */
  public Vec3d getNormalized() {
    final double normal = getNorm();
    if (isEqual(normal, 0d)) {
      throw new IllegalStateException("1/0 unknown");
    }

    // double dx = this.x * (1 / normal);
    // double dy = this.y * (1 / normal);
    // double dz = this.z * (1 / normal);
    // return new DoubleVector3D(dx, dy, dz);
    return scale(1 / normal);
  }

  /**
   * Formula:
   * <p>
   * Q = x1 - x0 W = y1 - y0 T = z1 - z0 V = (Q * Q) + (W * W) + (T * T)
   *
   * @param vector
   * @return
   */
  public double distanceSquaredTo(Vec3d vector) {
    // final double dx = vector.getX() - this.x;
    // final double dy = vector.getY() - this.y;
    // final double dz = vector.getZ() - this.z;
    // return (dx * dx) + (dy * dy) + (dz * dz);

    final Vec3d d = vector.sub(this);

    return d.getNormSquared();
  }

  /**
   * @param vector
   * @return
   */
  public double distanceTo(Vec3d vector) {
    return sqrt(distanceSquaredTo(vector));
  }

  /**
   * Formula:
   * <p>
   * r = |x| v = |y| n = |z| threshold = getNorm = math.sqrt((x0 * x0) + (y0 * y0) + (z0 * z0)) Q =
   * 0 W = (1/ math.sqrt(y * y + z * z)) * z T = -(1/ math.sqrt(y * y + z * z)) * y
   * <p>
   * Else if---- Q = -(1/ math.sqrt(x * x + z * z)) * z W = 0 T = (1/ math.sqrt(x * x + z * z)) * x
   * <p>
   * Else ------ Q = (1/ math.sqrt(x * x + z * z)) * y W = -(1/ math.sqrt(x * x + y * y)) * x T = 0
   *
   * @return
   */
  public Vec3d getOrthogonal() {
    final double threshold = 0.6 * getNorm();
    if (threshold == 0) {
      throw new IllegalArgumentException();
    }

    if (isSmallerEqual(Math.abs(x), threshold)) {
      final double inverse = 1 / Math.sqrt(y * y + z * z);
      return new Vec3d(0, inverse * z, -inverse * y);
    } else if (isSmallerEqual(Math.abs(y), threshold)) {
      final double inverse = 1 / Math.sqrt(x * x + z * z);
      return new Vec3d(-inverse * z, 0, inverse * x);
    }

    final double inverse = 1 / Math.sqrt(x * x + y * y);
    return new Vec3d(inverse * y, -inverse * x, 0);
  }

  /**
   * @return
   */
  public Vec3d getZero() {
    return new Vec3d(0d, 0d, 0d);
  }

  /**
   * @return
   */
  public double[] toArray() {
    return new double[]{x, y, z};
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

  /**
   * @return
   */
  public double getZ() {
    return z;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    hash = 59 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    hash = 59 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
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
    final Vec3d other = (Vec3d) obj;
    if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
      return false;
    }
    if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
      return false;
    }
    if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
      return false;
    }
    return true;
  }
}
