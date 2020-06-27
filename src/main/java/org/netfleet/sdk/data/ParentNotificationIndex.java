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
package org.netfleet.sdk.data;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public enum ParentNotificationIndex {

  HOME_ZONE(0),
  TAKE_FROM_HOME(1),
  SCHOOL_ZONE(2),
  DROP_TO_SCHOOL(3),
  TAKE_FROM_SCHOOL(4),
  RETURNING_HOME_ZONE(5),
  DROP_TO_HOME(6),
  ABSENCE(7),
  OTHER(-1);

  private final int value;

  ParentNotificationIndex(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(getValue());
  }

  public static ParentNotificationIndex valueOf(int value) {
    for (ParentNotificationIndex index: values()) {
      if (index.getValue() == value) {
        return index;
      }
    }

    return null;
  }
}
