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
package org.netfleet.sdk.commons.collection;

import java.util.Set;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Sets {

  private Sets() {
  }

  /**
   * @param <T>       generic sign of the elements.
   * @param structure the {@link Set} to be searched.
   * @param element   the element to be searched for.
   * @return {@code true} if given element is contained. {@code false} otherwise.
   */
  public static <T> boolean containsEquals(final Set<T> structure, T element) {
    for (T eachElement : structure) {
      if (eachElement.equals(element)) {
        return true;
      }
    }

    return structure.contains(element);
  }

  /**
   * @param <T>      generic sign of the elements.
   * @param nullable the nullable {@link Set}.
   * @param map      the optional {@link Set}.
   * @return
   */
  public static <T> Set<T> getIfNull(final Set<T> nullable, final Set<T> map) {
    if (nullable == null) {
      return map;
    }

    return nullable;
  }

  /**
   * @param <T>   generic sign of the elements.
   * @param stack the nullable {@link Set}.
   * @return {@code true} if given set is {@literal null} or empty.
   * {@code false} otherwise.
   */
  public static <T> boolean nullOrEmpty(final Set<T> stack) {
    return (stack == null) || stack.isEmpty();
  }
}