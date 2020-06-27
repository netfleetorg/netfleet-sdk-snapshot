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

import org.netfleet.sdk.geom.crs.CoordinateReferenceSystem;
import org.netfleet.sdk.geom.crs.GeographicCoordinateReferenceSystem;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public class SimplePosition implements Position {

  public static final double NULL_ORDINATE = Double.NaN;

  private final CoordinateReferenceSystem coordinateReferenceSystem;
  private final double[] coordinates;
  private final int dimension;

  public SimplePosition(int dimension) {
    this(new double[0], dimension);
  }

  public SimplePosition(double[] coordinates, int dimension) {
    this(GeographicCoordinateReferenceSystem.WGS84, dimension, coordinates);
  }

  public SimplePosition(CoordinateReferenceSystem coordinateReferenceSystem, int dimension) {
    this(coordinateReferenceSystem, dimension, new double[0]);
  }

  public SimplePosition(CoordinateReferenceSystem crs, int dimension, double... coordinates) {
    if (coordinates.length == 0) {
      this.coordinates = new double[0];
    } else {
      double[] c = new double[dimension];
      System.arraycopy(coordinates, 0, c, 0, coordinates.length);
      this.coordinates = c;
    }

    this.coordinateReferenceSystem = crs;
    this.dimension = dimension;
  }

  public SimplePosition(double x, double y) {
    this(GeographicCoordinateReferenceSystem.WGS84, 2, x, y);
  }

  public SimplePosition(double x, double y, double z) {
    this(GeographicCoordinateReferenceSystem.WGS84, 3, x, y, z);
  }

  @Override
  public CoordinateReferenceSystem getCoordinateReferenceSystem() {
    return coordinateReferenceSystem;
  }

  @Override
  public int getDimension() {
    return dimension;
  }

  @Override
  public double[] getCoordinate() {
    return coordinates;
  }

  @Override
  public double getOrdinate(int dimension) throws IndexOutOfBoundsException {
    return coordinates[dimension];
  }

  @Override
  public void setOrdinate(int dimension, double value)
      throws IndexOutOfBoundsException, UnsupportedOperationException {
    this.coordinates[dimension] = value;
  }
}
