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
public class BigLongitude extends Number {

  private static double MIN_LONGITUDE = Double.valueOf("-180.0000");
  private static double MAX_LONGITUDE = Double.valueOf("180.0000");

  private BigDecimal lon;

  public BigLongitude() {
  }

  public BigLongitude(BigDecimal longitude) {
    verify(longitude);
    this.lon = longitude;
  }

  /**
   * @param longitude
   */
  public BigLongitude(Number longitude) {
    this(new BigDecimal(longitude.doubleValue()));
  }

  /**
   * @param longitude
   * @return
   */
  public static BigLongitude of(double longitude) {
    return new BigLongitude(longitude);
  }

  /**
   * @param longitude
   * @return
   */
  public static BigLongitude of(float longitude) {
    return new BigLongitude(longitude);
  }

  /**
   * @return
   */
  public Angle toAngle() {
    if (Objects.nonNull(getLon())) {
      return Angle.fromDegree(getLon().doubleValue());
    }

    return null;
  }

  /**
   * @return
   */
  public BigDecimal getLon() {
    return lon;
  }

  /**
   * @param longitude
   */
  public void setLon(BigDecimal longitude) {
    verify(longitude);
    this.lon = longitude;
  }

  /**
   * @param longitude
   */
  private void verify(BigDecimal longitude) {
    Assert.requireNonNull(longitude);
    Assert.requireBetween(MIN_LONGITUDE, MAX_LONGITUDE, longitude.doubleValue());
  }

  @Override
  public int intValue() {
    if (Objects.nonNull(getLon())) {
      return getLon().intValue();
    }

    return BigDecimal.ZERO.intValue();
  }

  @Override
  public long longValue() {
    if (Objects.nonNull(getLon())) {
      return getLon().longValue();
    }

    return BigDecimal.ZERO.longValue();
  }

  @Override
  public float floatValue() {
    if (Objects.nonNull(getLon())) {
      return getLon().floatValue();
    }

    return BigDecimal.ZERO.floatValue();
  }

  @Override
  public double doubleValue() {
    if (Objects.nonNull(getLon())) {
      return getLon().doubleValue();
    }

    return BigDecimal.ZERO.doubleValue();
  }
}
