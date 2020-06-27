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
public final class Longs {

  private Longs() {
  }

  /**
   * @param array
   * @param element
   * @return
   */
  public static int indexOf(final long[] array, final long element) {
    final int length = array.length;
    for (int t = 0; t < length; t++) {
      if (Long.compare(array[t], element) == 0) {
        return t;
      }
    }

    return -1;
  }

  /**
   * @param array
   * @param element
   * @return
   */
  public static boolean contains(final long[] array, final long element) {
    final int length = array.length;
    for (int t = 0; t < length; t++) {
      if (Long.compare(array[t], element) == 0) {
        return true;
      }
    }

    return false;
  }

  /**
   * @param array
   * @return
   */
  public static int findGreatestIndex(final long[] array) {
    int found = -1;
    if (Objects.isNull(array)) {
      return found;
    }

    final int length = array.length;
    found = 0;
    boolean isFound = false;
    for (int t = 0; t < length; t++) {
      if (Long.compare(array[t], array[found]) > 0) {
        found = t;
        isFound = true;
      }
    }

    return isFound ? found : -1;
  }

  /**
   * @param array
   * @return
   */
  public static int findSmallestIndex(final long[] array) {
    int found = -1;
    if (Objects.isNull(array)) {
      return found;
    }

    final int length = array.length;
    found = 0;
    boolean isFound = false;
    for (int t = 0; t < length; t++) {
      if (Long.compare(array[t], array[found]) < 0) {
        found = t;
        isFound = true;
      }
    }

    return isFound ? found : -1;
  }

  /**
   * @param array
   * @param element
   * @return
   */
  public static long[] add(final long[] array, final long element) {
    final int length = array.length;
    final long[] newArray = new long[length + 1];

    System.arraycopy(array, 0, newArray, 0, length);

    newArray[length] = element;
    return newArray;
  }

  /**
   * @param array
   * @return
   */
  public static boolean isEmpty(final long[] array) {
    return Objects.isNull(array) || (array.length == 0);
  }

  /**
   * @param array
   * @param index
   * @return
   */
  public static long[] remove(final long[] array, final int index) {
    final int length = array.length;
    if ((index > length) || (index < 0)) {
      throw new IllegalArgumentException("index is invalid");
    }

    final long[] newArray = new long[length - 1];
    System.arraycopy(array, 0, newArray, 0, index);
    if (index > length - 1) {
      return newArray;
    }

    final int destination = length - index - 1;
    System.arraycopy(array, index + 1, newArray, index, destination);

    return newArray;
  }

  /**
   * @param array
   * @param element
   * @return
   */
  public static long[] remove(final long[] array, long element) {
    final int idx = indexOf(array, element);
    if (idx == -1) {
      throw new IllegalArgumentException("there is no such element in the given array.");
    }

    return remove(array, idx);
  }

  /**
   * @param array
   * @return
   */
  public static long findGreatest(final long[] array) {
    long found = -1;
    if (Objects.isNull(array)) {
      return found;
    }

    final int length = array.length;
    found = array[0];
    for (int t = 0; t < length; t++) {
      found = Math.max(array[t], found);
    }

    return found;
  }

  /**
   * @param array
   * @return
   */
  public static long findSmallest(final long[] array) {
    long found = -1;
    if (Objects.isNull(array)) {
      return found;
    }

    final int length = array.length;
    found = array[0];
    for (int t = 0; t < length; t++) {
      found = Math.min(array[t], found);
    }

    return found;
  }

  /**
   * @param number0
   * @param number1
   * @param long
   * @return
   */
  public static boolean isEqual(final long number0, final long number1) {
    return Long.compare(number0, number1) == 0;
  }

  /**
   * @param number0
   * @param number1
   * @return
   */
  public static boolean isGreater(final long number0, final long number1) {
    return Long.compare(number0, number1) > 0;
  }

  /**
   * @param number0
   * @param number1
   * @return
   */
  public static boolean isGreaterEqual(final long number0, final long number1) {
    return Long.compare(number0, number1) >= 0;
  }

  /**
   * @param number0
   * @param number1
   * @param long
   * @return
   */
  public static boolean isSmaller(final long number0, final long number1) {
    return Long.compare(number0, number1) < 0;
  }

  /**
   * @param number0
   * @param long
   * @param number1
   * @return
   */
  public static boolean isSmallerEqual(final long number0, final long number1) {
    return Long.compare(number0, number1) <= 0;
  }

  /**
   * @param number0
   * @return
   */
  public static boolean isNegative(final long number0) {
    return Long.compare(number0, 0l) < 0;
  }

  /**
   * @param number0
   * @return
   */
  public static boolean isPositive(final long number0) {
    return Long.compare(number0, 0l) > 0;
  }
}
