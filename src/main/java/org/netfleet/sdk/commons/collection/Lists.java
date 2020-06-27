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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.netfleet.sdk.util.Objects;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Lists {

  private Lists() {
  }

  /**
   * @param <T>  the generic sign of the elements.
   * @param list the {@link List} to be searched.
   * @return Deep copied {@link List}.
   * @throws java.io.IOException
   * @throws java.lang.ClassNotFoundException
   */
  public static <T> List<T> deepCopy(final List<T> list) throws IOException, ClassNotFoundException {
    List<T> dCopy = new ArrayList<>(list.size());

    for (T each : list) {
      dCopy.add(Objects.deepCopy(each));
    }

    return dCopy;
  }

  /**
   * @param <T>   the generic sign of the elements.
   * @param list  the {@link List} to be searched.
   * @param empty the {@link List} to be added.
   * @return Deep copied {@link List}.
   * @throws java.io.IOException
   * @throws java.lang.ClassNotFoundException
   */
  public static <T> List<T> deepCopy(final List<T> list, final List<T> empty) throws IOException, ClassNotFoundException {
    for (T each : list) {
      empty.add(Objects.deepCopy(each));
    }

    return empty;
  }

  /**
   * @param <T>    the generic sign of the elements.
   * @param source the source {@link List}.
   * @param target the target {@link List}.
   * @return a {@link List} of elements which intersects between given {@link List}.
   */
  public static <T> List<T> intersection(final List<T> source, final List<T> target) {
    final List<T> stack = new ArrayList<>();

    for (T sourceElement : source) {
      for (T targetElement : target) {
        if (Objects.equals(sourceElement, targetElement)) {
          stack.add(targetElement);
        }
      }
    }

    return stack;
  }

  /**
   * @param <T>    the generic sign of the elements.
   * @param source the source {@link List}.
   * @param target the target {@link List}.
   * @param stack  the container {@link List}.
   * @return
   */
  public static <T> List<T> union(final List<T> source, final List<T> target, List<T> stack) {
    if (stack == null) {
      stack = new ArrayList<>();
    }

    if (source != null) {
      for (T item : source) {
        stack.add(item);
      }
    }

    if (target != null) {
      for (T item : target) {
        stack.add(item);
      }
    }

    return stack;
  }

  /**
   * @param <T>     the generic sign of the elements.
   * @param stack   the {@link List} to be searched.
   * @param element the element to be searched.
   * @return {@code true} if given element contained. {@code false} otherwise.
   */
  public static <T> boolean contains(final List<T> stack, T element) {
    if (stack == null) {
      return false;
    }

    boolean found;
    try {
      found = stack.contains(element);
    } catch (Exception e) {
      found = false;
    }

    return found;
  }

  /**
   * @param collection the {@link List}
   * @return {@code true} if given collection is {@literal null} or empty.
   * {@code false} otherwise.
   */
  public static boolean nullOrEmpty(List collection) {
    return collection == null || collection.isEmpty();
  }
}