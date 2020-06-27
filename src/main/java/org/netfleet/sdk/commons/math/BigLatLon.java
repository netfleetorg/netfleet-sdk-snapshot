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

import org.netfleet.sdk.commons.tuple.LatlonEntry;
import org.netfleet.sdk.util.Objects;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class BigLatLon implements LatlonEntry, Serializable {

  private BigLatitude bigLatitude;
  private BigLongitude bigLongitude;

  public BigLatLon() {
  }

  /**
   * @param lat
   * @param lon
   */
  public BigLatLon(BigLatitude lat, BigLongitude lon) {
    this.bigLatitude = lat;
    this.bigLongitude = lon;
  }

  /**
   * @param latitude
   * @param longitude
   */
  public BigLatLon(BigDecimal latitude, BigDecimal longitude) {
    this(new BigLatitude(latitude), new BigLongitude(longitude));
  }

  /**
   * @param lat
   * @param lon
   */
  public BigLatLon(Number lat, Number lon) {
    this(new BigLatitude(new BigDecimal(lat.doubleValue())),
      new BigLongitude(new BigDecimal(lon.doubleValue())));
  }

  /**
   * @param lat
   * @param lon
   * @return
   */
  public static BigLatLon of(double lat, double lon) {
    return new BigLatLon(lat, lon);
  }

  /**
   * @param lat
   * @param lon
   * @return
   */
  public static BigLatLon of(float lat, float lon) {
    return new BigLatLon(lat, lon);
  }

  /**
   * @return
   */
  public BigLatitude getBigLatitude() {
    return bigLatitude;
  }

  /**
   * @param bigLatitude
   */
  public void setBigLatitude(BigLatitude bigLatitude) {
    this.bigLatitude = bigLatitude;
  }

  /**
   * @return
   */
  public BigLongitude getBigLongitude() {
    return bigLongitude;
  }

  /**
   * @param bigLongitude
   */
  public void setBigLongitude(BigLongitude bigLongitude) {
    this.bigLongitude = bigLongitude;
  }

  @Override
  public Double lat() {
    if (Objects.nonNull(getBigLatitude())) {
      return getBigLatitude().doubleValue();
    }

    return BigDecimal.ZERO.doubleValue();
  }

  @Override
  public Double lon() {
    if (Objects.nonNull(getBigLongitude())) {
      return getBigLongitude().doubleValue();
    }

    return BigDecimal.ZERO.doubleValue();
  }
}
