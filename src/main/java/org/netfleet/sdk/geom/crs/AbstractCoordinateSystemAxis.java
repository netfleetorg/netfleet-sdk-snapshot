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

package org.netfleet.sdk.geom.crs;

import javax.measure.Unit;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public abstract class AbstractCoordinateSystemAxis implements CoordinateSystemAxis {

  private String axisName;
  private AxisDirection direction;
  private Unit<?> unit;
  private double minimumValue;
  private double maximumValue;

  public AbstractCoordinateSystemAxis(String axisName, AxisDirection direction, Unit<?> unit,
                                      double minimumValue, double maximumValue) {
    this.axisName = axisName;
    this.direction = direction;
    this.unit = unit;
    this.minimumValue = minimumValue;
    this.maximumValue = maximumValue;
  }

  public void setAxisName(String axisName) {
    this.axisName = axisName;
  }

  @Override
  public String getAxisName() {
    return axisName;
  }

  public void setDirection(AxisDirection direction) {
    this.direction = direction;
  }

  public void setUnit(Unit<?> unit) {
    this.unit = unit;
  }

  public void setMinimumValue(double minimumValue) {
    this.minimumValue = minimumValue;
  }

  public void setMaximumValue(double maximumValue) {
    this.maximumValue = maximumValue;
  }

  @Override
  public AxisDirection getDirection() {
    return direction;
  }

  @Override
  public Unit<?> getUnit() {
    return unit;
  }

  @Override
  public double getMinimumValue() {
    return minimumValue;
  }

  @Override
  public double getMaximumValue() {
    return maximumValue;
  }
}
