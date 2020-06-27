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
public final class Floats {

  private Floats() {
  }

  /**
   * @param nullable can be {@literal null}
   * @param optional can be {@literal null}
   * @return if both arguments are {@literal null}, it return {@literal 0f}.
   */
  public static float getIfNull(Float nullable, Float optional) {
    if (nullable == null) {
      if (optional == null) {
        return 0f;
      } else {
        return optional;
      }
    }

    return nullable;
  }

  /**
   * @param array   the array to be searched, can be {@literal null}.
   * @param element the element to be searched for, cannot be {@literal null}.
   * @return index of given element in the given array. {@literal -1} if element
   * does not exists in the array .
   */
  public static int indexOf(final float[] array, final float element) {
    if (array == null) {
      return -1;
    }

    final int length = array.length;
    if (length == 0) {
      return -1;
    }

    for (int t = 0; t < length; t++) {
      if (Float.compare(array[t], element) == 0) {
        return t;
      }
    }

    return -1;
  }

  /**
   * @param array   the array to be searched, can be {@literal null}.
   * @param element the element to be searched for, cannot be {@literal null}.
   * @return {@code true} if given element contained in the given array.
   * Otherwise {@code false}.
   */
  public static boolean contains(final float[] array, final float element) {
    if (array == null) {
      return false;
    }

    final int length = array.length;
    if (length == 0) {
      return false;
    }

    for (int t = 0; t < length; t++) {
      if (Float.compare(array[t], element) == 0) {
        return true;
      }
    }

    return false;
  }

  /**
   * @param array the array to be searched, can be {@literal null}.
   * @return Index of greatest element in the given array. {@literal -1} if
   * something goes wrong.
   */
  public static int findGreatestIndex(final float[] array) {
    int found = -1;
    if (Objects.isNull(array)) {
      return found;
    }

    final int length = array.length;
    found = 0;
    boolean isFound = false;
    for (int t = 0; t < length; t++) {
      if (Float.compare(array[t], array[found]) > 0) {
        found = t;
        isFound = true;
      }
    }

    return isFound ? found : -1;
  }

  /**
   * @param array the array to be searched, can be {@literal null}.
   * @return Index of greatest element in the given array. {@literal -1} if
   * something goes wrong.
   */
  public static int findSmallestIndex(final float[] array) {
    int found = -1;
    if (Objects.isNull(array)) {
      return found;
    }

    final int length = array.length;
    found = 0;
    boolean isFound = false;
    for (int t = 0; t < length; t++) {
      if (Float.compare(array[t], array[found]) < 0) {
        found = t;
        isFound = true;
      }
    }

    return isFound ? found : -1;
  }

  /**
   * @param array   the array to be added, cannot be {@literal null}.
   * @param element the element to be added to the array, can be {@literal null}.
   * @return A new array created with given array plus given element.
   */
  public static float[] add(final float[] array, final float element) {
    Assert.requireNonNull(array);

    final int length = array.length;
    final float[] newArray = new float[length + 1];

    System.arraycopy(array, 0, newArray, 0, length);

    newArray[length] = element;
    return newArray;
  }

  /**
   * @param array the array to be searched, can be {@literal null}.
   * @return {@code true} if given array is either {@literal null} or empty.
   * Otherwise {@code false}.
   */
  public static boolean isEmpty(final float[] array) {
    return Objects.isNull(array) || (array.length == 0);
  }

  /**
   * @param array the array to be searched, can be {@literal null}.
   * @param index the index to be removed.
   * @return A new array created from given array minus element that specified
   * with given index.
   * @throws IllegalArgumentException if index is invalid.
   */
  public static float[] remove(final float[] array, final int index) {
    final int length = array.length;
    if ((index > length) || (index < 0)) {
      throw new IllegalArgumentException("index is invalid");
    }

    final float[] newArray = new float[length - 1];
    System.arraycopy(array, 0, newArray, 0, index);
    if (index > length - 1) {
      return newArray;
    }

    final int destination = length - index - 1;
    System.arraycopy(array, index + 1, newArray, index, destination);

    return newArray;
  }

  /**
   * @param array   the array to be searched, can be {@literal null}.
   * @param element the element to be removed. can be {@literal null}.
   * @return A new array created from given array minus given element.
   * @throws IllegalArgumentException if given element is not contained in the
   *                                  given array.
   */
  public static float[] remove(final float[] array, float element) {
    final int idx = indexOf(array, element);
    if (idx == -1) {
      throw new IllegalArgumentException("there is no such element in the given array.");
    }

    return remove(array, idx);
  }

  /**
   * @param array the array to be searched, can be {@literal null}.
   * @return greatest element in the given array. {@literal -1} if something goes
   * wrong.
   */
  public static float findGreatest(final float[] array) {
    float found = -1;
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
   * @param array the array to be searched, can be {@literal null}.
   * @return Smallest element in the given array. {@literal -1} if something goes
   * wrong.
   */
  public static float findSmallest(final float[] array) {
    float found = -1;
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
   * @param number0 the first number.
   * @param number1 the second number.
   * @return {@code true} if both arguments are equal. Otherwise {@code false}.
   */
  public static boolean isEqual(final float number0, final float number1) {
    return Float.compare(number0, number1) == 0;
  }

  /**
   * @param number0 the first number.
   * @param number1 the second number.
   * @return {@code true} if first number is greater than second number.
   * Otherwise {@code false}.
   */
  public static boolean isGreater(final float number0, final float number1) {
    return Float.compare(number0, number1) > 0;
  }

  /**
   * @param number0 the first number.
   * @param number1 the second number.
   * @return {@code true} if first number is greater equal than second number.
   * Otherwise {@code false}.
   */
  public static boolean isGreaterEqual(final float number0, final float number1) {
    return Float.compare(number0, number1) >= 0;
  }

  /**
   * @param number0 the first number.
   * @param number1 the second number.
   * @return {@code true} if first number is smaller than second number.
   * Otherwise {@code false}.
   */
  public static boolean isSmaller(final float number0, final float number1) {
    return Float.compare(number0, number1) < 0;
  }

  /**
   * @param number0 the first number.
   * @param number1 the second number.
   * @return {@code true} if first number is smaller equal than second number.
   * Otherwise {@code false}.
   */
  public static boolean isSmallerEqual(final float number0, final float number1) {
    return Float.compare(number0, number1) <= 0;
  }

  /**
   * @param number0 the number.
   * @return {@code true} if given number is smaller than {@literal 0}.
   * Otherwise {@code false}.
   */
  public static boolean isNegative(final float number0) {
    return Float.compare(number0, 0f) < 0;
  }

  /**
   * @param number0 the number.
   * @return {@code true} if given number is greater than {@literal 0}.
   * Otherwise {@code false}.
   */
  public static boolean isPositive(final float number0) {
    return Float.compare(number0, 0f) > 0;
  }
}