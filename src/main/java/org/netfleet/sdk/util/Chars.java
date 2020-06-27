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
public final class Chars {

  private Chars() {
  }

  /**
   * @param shortType
   * @return
   */
  public static Character from(Short shortType) {
    short s = shortType;
    int i = s;

    return from(i);
  }

  /**
   * @param integer
   * @return
   */
  public static Character from(Integer integer) {
    int i = integer;
    char c = (char) i;
    Character r = c;

    return r;
  }

  /**
   * @param character can be {@literal null}
   * @param orElse    can be {@literal null}
   * @return orElse if given character is null.Otherwise return character.
   */
  public static Character orElse(Character character, Character orElse) {
    return (character == null) ? orElse : character;
  }

  /**
   * @param nullableChararecter can be {@literal null}
   * @param chararecter         can be {@literal null}
   * @return
   */
  public static Character getIfNull(Character nullableChararecter, Character chararecter) {
    return orElse(nullableChararecter, chararecter);
  }

  /**
   * @param character
   * @return MIN_VALUE if given character is null.Otherwise return character.
   */
  public static Character secure(Character character) {
    return (character == null) ? Character.MIN_VALUE : character;
  }
}
