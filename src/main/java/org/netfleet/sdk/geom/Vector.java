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

import org.netfleet.sdk.annotation.Development;
import org.netfleet.sdk.commons.math.Angle;
import org.netfleet.sdk.commons.math.Vec3d;
import org.netfleet.sdk.geom.crs.AxisDirection;
import org.netfleet.sdk.geom.crs.CoordinateReferenceSystem;
import org.netfleet.sdk.geom.crs.GeographicCoordinateReferenceSystem;
import org.netfleet.sdk.util.Objects;

import static java.lang.Math.*;
import static org.netfleet.sdk.util.Doubles.isEqual;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public final class Vector {

  public final double x;
  public final double y;
  public final double z;

  public Vector(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector(Vec3d vec3d) {
    this(vec3d.x, vec3d.y, vec3d.z);
  }

  public Vector(Vector geoVector) {
    this(geoVector.x, geoVector.y, geoVector.z);
    // İhtiyaçtan fazlasında gözün olmasın
  }

  @Development(
      status = Development.Status.UNKNOWN,
      content = "Not tested yet properly."
  )
  public Vector offset(Vector _axis, Angle thetaAngle) {

    Vector _v0 = normalize();
    double[] _axisVecAsArr = new double[]{_v0.x, _v0.y, _v0.z};

    Vector _v1 = _axis.normalize();
    double _sinTheta = thetaAngle.sin();
    double _costTheta = thetaAngle.cos();

    double[][] _qdMatrix = new double[][]{
        {_v1.x * _v1.x * (1 - _costTheta) + _costTheta, _v1.x * _v1.y * (1 - _costTheta) - _v1.z * _sinTheta, _v1.x * _v1.z * (1 - _costTheta) + _v1.y * _sinTheta},
        {_v1.y * _v1.x * (1 - _costTheta) + _v1.z * _sinTheta, _v1.y * _v1.y * (1 - _costTheta) + _costTheta, _v1.y * _v1.z * (1 - _costTheta) - _v1.x * _sinTheta},
        {_v1.z * _v1.x * (1 - _costTheta) - _v1.y * _sinTheta, _v1.z * _v1.y * (1 - _costTheta) + _v1.x * _sinTheta, _v1.z * _v1.z * (1 - _costTheta) + _costTheta},};

    double[] _solution = new double[]{0, 0, 0};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        _solution[i] += _qdMatrix[i][j] * _axisVecAsArr[j];
      }
    }

    return new Vector(_solution[0], _solution[1], _solution[2]);
  }

  @Development(status = Development.Status.STABLE)
  public Position toLatlon() {
    /*
      final double _let0 = hypot(x, y);
      <p>
      final double _letQ = atan2(z, _let0);
      final double _letW = atan2(y, x);
      <p>
      return new ImmutableLatlon(toDegrees(_letQ), toDegrees(_letW));
     */

    double lat = atan2(this.z, sqrt(this.x * this.x + this.y * this.y));
    double lon = atan2(this.y, this.x);

    return new SimplePosition(GeographicCoordinateReferenceSystem.WGS84,2,
        Math.toDegrees(lat),
        Math.toDegrees(lon));
  }

  public static Vector fromLatlon(Position latlon) {
    CoordinateReferenceSystem crs = latlon.getCoordinateReferenceSystem();
    double lat = latlon.getOrdinate(AxisDirection.GEOCENTRIC_X.getOrder());
    double lon = latlon.getOrdinate(AxisDirection.GEOCENTRIC_Y.getOrder());

    return fromRadianLatlon(Math.toRadians(lat), Math.toRadians(lon));
  }

  public static Vector fromRadianLatlon(double lat0, double lon0) {
    final double _letX = cos(lat0) * cos(lon0);
    final double _letY = cos(lat0) * sin(lon0);
    final double _letZ = sin(lat0);

    return new Vector(_letX, _letY, _letZ);
  }

  private static Vector _getGreatCircle(Vector geoVector, double radianBearing) {
    final Vector _tmp = new Vector(0, 0, 1);

    Vector _easting = _tmp.cross(geoVector);
    Vector _northing = geoVector.cross(_easting);

    Vector _v0 = _easting.scale(cos(radianBearing) / _easting.norm());
    Vector _v1 = _northing.scale(sin(radianBearing) / _northing.norm());

    return _v1.translate(_v0.reverse());
  }

  public Vector getGreatCircle(Angle angle) {
    return new Vector(_getGreatCircle(this, angle.radian));
  }

  public Vector getGreatCircle(double degree) {
    return new Vector(_getGreatCircle(this, toRadians(degree)));
  }

  public Angle angleTo(Vector _v0, Vector _v1) {
    double _sinQ = cross(_v0).norm();
    double _cosQ = dot(_v0);

    if (!Objects.isNull(_v1)) {
      _sinQ = cross(_v0).dot(_v1) < 0 ? -_sinQ : _sinQ;
    }

    return Angle.fromRadian(atan2(_sinQ, _cosQ));
  }

  public Vector translate(double _x, double _y, double _z) {
    return new Vector(x + _x, y + _y, z + _z);
  }

  public Vector translate(Vector geoVector) {
    return translate(geoVector.x, geoVector.y, geoVector.z);
  }

  public Vector scale(double scalar) {
    return new Vector(x * scalar, y * scalar, z * scalar);
  }

  public Vector reverse() {
    return scale(-1);
  }

  public double dot(Vector geoVector) {
    final double dx = x * geoVector.x;
    final double dy = y * geoVector.y;
    final double dz = z * geoVector.z;

    return dx + dy + dz;
  }

  public Vector cross(Vector geoVector) {
    final double dx = y * geoVector.z - z * geoVector.getY();
    final double dy = z * geoVector.x - x * geoVector.getZ();
    final double dz = x * geoVector.y - y * geoVector.getX();

    return new Vector(dx, dy, dz);
  }

  public double normSq() {
    return dot(this);
  }

  public double norm() {
    return sqrt(normSq());
  }

  public Vector normalize() {
    final double normal = norm();
    if (isEqual(normal, 0d)) {
      throw new IllegalStateException("1/0 unknown");
    }

    return scale(1 / normal);
  }

  public double distSq(Vector geoVector) {
    return geoVector.translate(this.reverse()).normSq();
  }

  public double dist(Vector geoVector) {
    return sqrt(distSq(geoVector));
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

}
