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

import org.netfleet.sdk.util.Objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Utility class for {@link Iterator} and {@link Iterable}.
 *
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Iterables {

  private Iterables() {
  }

  /**
   * @param <T>        the generic sign.
   * @param <U>        the generic sign.
   * @param iterable   Collection to be transformed.
   * @param collection ?
   * @return
   */
  public static <T, U extends Collection<T>> U toCollection(Iterable<T> iterable, U collection) {
    if (iterable == null) {
      return (U) new ArrayList<T>();
    }

    return toCollection(iterable.iterator(), collection);
  }

  public static <T, U extends Collection<T>> U toCollection(Iterator<T> iterator, U collection) {
    if (iterator == null) {
      return (U) new ArrayList<T>();
    }

    final U stack = (U) ((collection == null) ? new ArrayList<T>() : collection);
    while (iterator.hasNext()) {
      stack.add(iterator.next());
    }

    return stack;
  }

  /**
   * @param <T>      the generic sign of the elements.
   * @param iterator the iterator to be searched.
   * @param element  to element to be searched for.
   * @return {@code true} if given element is contained. {@code false} otherwise.
   */
  public static <T> boolean contains(Iterator<T> iterator, T element) {
    if (iterator == null) {
      return false;
    }

    boolean found = false;
    while (iterator.hasNext()) {
      final T current = iterator.next();

      if (Objects.equals(current, element)) {
        found = true;
        break;
      }
    }

    return found;
  }

  /**
   * @param <T>      the generic sign of the elements.
   * @param iterable the iterable to be searched.
   * @param element  to element to be searched for.
   * @return {@code true} if given element is contained. {@code false} otherwise.
   */
  public static <T> boolean contains(Iterable<T> iterable, T element) {
    if (iterable == null) {
      return false;
    }

    return contains(iterable.iterator(), element);
  }
}