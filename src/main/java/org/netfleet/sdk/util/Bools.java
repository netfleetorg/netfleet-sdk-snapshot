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
package org.netfleet.sdk.util;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Bools {

  private Bools() {
  }

  /**
   * @param bool the boolean value to be check.
   * @return true, if given argument is not null and true.
   */
  public static boolean isTrue(final Boolean bool) {
    return Boolean.TRUE.equals(bool);
  }

  /**
   * @param bool the boolean value to be check.
   * @return false, if given argument is not null and false.
   */
  public static boolean isFalse(final Boolean bool) {
    return Boolean.FALSE.equals(bool);
  }

  /**
   * @param bool the boolean value to be check.
   * @return checks whether is given bool variable {@literal null} or not.
   */
  public static boolean toBool(final Boolean bool) {
    return bool != null && bool;
  }
}