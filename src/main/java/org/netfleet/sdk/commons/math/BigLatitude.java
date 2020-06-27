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

import org.netfleet.sdk.util.Assert;
import org.netfleet.sdk.util.Objects;

import java.math.BigDecimal;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class BigLatitude extends Number {

  private static double MIN_LATITUDE = Double.valueOf("-90.0000");
  private static double MAX_LATITUDE = Double.valueOf("90.0000");

  private BigDecimal lat;

  public BigLatitude() {
  }

  /**
   * @param latitude
   */
  public BigLatitude(BigDecimal latitude) {
    verify(latitude);
    this.lat = latitude;
  }

  /**
   * @param latitude
   */
  public BigLatitude(Number latitude) {
    this(new BigDecimal(latitude.doubleValue()));
  }

  /**
   * @param latitude
   * @return
   */
  public static BigLatitude of(double latitude) {
    return new BigLatitude(latitude);
  }

  /**
   * @param latitude
   * @return
   */
  public static BigLatitude of(float latitude) {
    return new BigLatitude(latitude);
  }

  /**
   * @return
   */
  public Angle toAngle() {
    if (Objects.nonNull(getLat())) {
      return Angle.fromDegree(getLat().doubleValue());
    }

    return null;
  }

  /**
   * @return
   */
  public BigDecimal getLat() {
    return lat;
  }

  /**
   * @param latitude
   */
  public void setLat(BigDecimal latitude) {
    verify(latitude);
    this.lat = latitude;
  }

  /**
   * @param latitude
   */
  private void verify(BigDecimal latitude) {
    Assert.requireNonNull(latitude);
    Assert.requireBetween(MIN_LATITUDE, MAX_LATITUDE, latitude.doubleValue());
  }

  @Override
  public int intValue() {
    if (Objects.nonNull(getLat())) {
      return getLat().intValue();
    }

    return BigDecimal.ZERO.intValue();
  }

  @Override
  public long longValue() {
    if (Objects.nonNull(getLat())) {
      return getLat().longValue();
    }

    return BigDecimal.ZERO.longValue();
  }

  @Override
  public float floatValue() {
    if (Objects.nonNull(getLat())) {
      return getLat().floatValue();
    }

    return BigDecimal.ZERO.floatValue();
  }

  @Override
  public double doubleValue() {
    if (Objects.nonNull(getLat())) {
      return getLat().doubleValue();
    }

    return BigDecimal.ZERO.doubleValue();
  }
}
