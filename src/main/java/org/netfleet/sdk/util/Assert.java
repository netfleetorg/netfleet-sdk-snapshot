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

import org.netfleet.sdk.commons.java8.Optional;
import org.netfleet.sdk.commons.java8.Predicate;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Assert {

  private Assert() {
  }

  /**
   * @param object the {@link Object}, cannot be {@literal null}.
   * @see Optional
   */
  public static void requireNonNull(final Object object) {
    requireNonNull(object, "");
  }

  /**
   * @param object  the {@link Object}, cannot be {@literal null}.
   * @param message the {@link String} message of {@link NullPointerException},
   *                may be {@literal null}.
   */
  public static void requireNonNull(final Object object, final String message) {
    if (object == null) {
      throw new NullPointerException(message);
    }
  }

  public static <T> T getIfNonNull(final T object, final String message) {
    if (object == null) {
      throw new NullPointerException(message);
    } else {
      return object;
    }
  }

  /**
   * @param <T>    the generic sign of object.
   * @param object the object.
   * @return parameter if it is not {@literal null}.
   * @throws NullPointerException if given parameter is {@literal null}.
   * @see Optional
   * @see #requireNonNull(java.lang.Object)
   */
  public static <T> T getIfNonNull(final T object) {
    if (object == null) {
      throw new NullPointerException("");
    }

    return object;
  }

  /**
   * @param state the {@code boolean} value.
   * @throws IllegalStateException if given state is {@code false}.
   */
  public static void requireTrue(final boolean state) {
    requireTrue(state, "state is equals to false.");
  }

  /**
   * @param state   the {@code boolean} value.
   * @param message the {@link String} message of {@link IllegalStateException},
   *                may be {@literal null}.
   * @throws IllegalStateException if given state is {@code false}.
   */
  public static void requireTrue(final boolean state, final String message) {
    if (state == false) {
      throw new IllegalStateException(message);
    }
  }

  /**
   * @param state the {@code boolean} value.
   * @throws IllegalStateException if given state is {@code false}.
   */
  public static void requireFalse(final boolean state) {
    requireFalse(state, "state is equals to true.");
  }

  /**
   * @param state   the {@code boolean} value.
   * @param message the {@link String} message of {@link IllegalStateException},
   *                may be {@literal null}.
   * @throws IllegalStateException if given state is {@code true}.
   */
  public static void requireFalse(final boolean state, final String message) {
    if (state == true) {
      throw new IllegalStateException(message);
    }
  }

  /**
   * @param <T>       generic sign of the object.
   * @param predicate the {@link Predicate}, cannot be {@literal null}.
   * @param object    the object, cannot be {@literal null}.
   * @throws IllegalStateException if given one of the parameter is {@literal null}
   *                               or given {@link Predicate} is equals to {@code false}.
   */
  public static <T> void requireTrue(final Predicate<T> predicate, final T object) {
    requireTrue(predicate, object, "state is equals to false.");
  }

  /**
   * @param <T>       generic sign of the object.
   * @param predicate the {@link Predicate}, cannot be {@literal null}.
   * @param object    the object, cannot be {@literal null}.
   * @param message   the {@link String} message of {@link IllegalStateException},
   *                  may be {@literal null}.
   * @throws IllegalStateException if given one of the parameter is {@literal null}
   *                               or given {@link Predicate} is equals to {@code false}.
   */
  public static <T> void requireTrue(final Predicate<T> predicate, final T object, final String message) {
    if (!predicate.call(object)) {
      throw new IllegalStateException(message);
    }
  }

  /**
   * @param <T>       generic sign of the object.
   * @param predicate the {@link Predicate}, cannot be {@literal null}.
   * @param object    the object, cannot be {@literal null}.
   * @throws IllegalStateException if given one of the parameter is {@literal null}
   *                               or given {@link Predicate} is equals to {@code true}.
   */
  public static <T> void requireFalse(final Predicate<T> predicate, final T object) {
    requireFalse(predicate, object, "state is equals to true");
  }

  /**
   * @param <T>       generic sign of the object.
   * @param predicate the {@link Predicate}, cannot be {@literal null}.
   * @param object    the object, cannot be {@literal null}.
   * @param message   the {@link String} message of {@link IllegalStateException},
   *                  may be {@literal null}.
   * @throws IllegalStateException if given one of the parameter is {@literal null}
   *                               or given {@link Predicate} is equals to {@code true}.
   */
  public static <T> void requireFalse(final Predicate<T> predicate, final T object, final String message) {
    if (predicate.call(object)) {
      throw new IllegalStateException(message);
    }
  }

  /**
   * @param <T>          the generic sign of the elements in the array.
   * @param genericArray the array, cannot be {@literal null}.
   * @throws IllegalArgumentException if given array is either
   *                                  {@literal null} or empty.
   */
  public static <T> void requireNonEmpty(final T[] genericArray) {
    if (genericArray == null) {
      throw new IllegalArgumentException("given array is equals null.");
    } else if (Array.getLength(genericArray) == 0) {
      throw new IllegalArgumentException("array's length is equals 0.");
    }
  }

  /**
   * @param collection the {@link Collection} object, cannot be {@literal null}.
   * @throws IllegalArgumentException if given {@link Collection} is either
   *                                  {@literal null} or empty.
   */
  public static void requireNonEmpty(final Collection collection) {
    if (collection == null) {
      throw new IllegalArgumentException("given collection is equals null.");
    } else if (collection.isEmpty()) {
      throw new IllegalArgumentException("collections's size is equals to 0.");
    }
  }

  /**
   * @param map the {@link Map} object, cannot be {@literal null}.
   * @throws IllegalArgumentException if given {@link Map} is either
   *                                  {@literal null} or empty.
   */
  public static void requireNonEmpty(final Map map) {
    if (map == null) {
      throw new IllegalArgumentException("given map is equals null.");
    } else if (map.isEmpty()) {
      throw new IllegalArgumentException("map's size is equals to 0.");
    }
  }

  /**
   * @param value {@code double} value. the value cannot be equals one of the
   *              followings:
   *              <p>
   *              {@link Double#NaN},
   *              {@link Double#NEGATIVE_INFINITY},
   *              {@link Double#POSITIVE_INFINITY}.
   * @throws IllegalArgumentException if given {@code double} value is not finite.
   */
  public static void requireFinite(final double value) {
    if (Double.isNaN(value) || Double.isInfinite(value)) {
      throw new IllegalArgumentException("value is either NaN or Infinite.");
    }
  }

  /**
   * @param begin the {@code int} value of minimum range.
   * @param end   the {@code int} value of maximum range.
   * @param value the {@code int} value to be queried.
   * @throws IllegalArgumentException if given {@code int} value is not in range.
   */
  public static void requireBetween(final int begin, final int end, final int value) {
    if (value <= begin || value >= end) {
      throw new IllegalArgumentException("value is not between begin and end.");
    }
  }

  /**
   * @param begin the {@code double} value of minimum range.
   * @param end   the {@code double} value of maximum range.
   * @param value the {@code double} value to be queried.
   * @throws IllegalArgumentException if given {@code double} value is not in range.
   */
  public static void requireBetween(final double begin, final double end, final double value) {
    if (value <= begin || value >= end) {
      throw new IllegalArgumentException("value is not between begin and end.");
    }
  }

  /**
   * @param begin the {@code float} value of minimum range.
   * @param end   the {@code float} value of maximum range.
   * @param value the {@code float} value to be queried.
   * @throws IllegalArgumentException if given {@code float} value is not in range.
   */
  public static void requireBetween(final float begin, final float end, final float value) {
    if (value <= begin || value >= end) {
      throw new IllegalArgumentException("value is not between begin and end.");
    }
  }

  /**
   * @param begin the {@code long} value of minimum range.
   * @param end   the {@code long} value of maximum range.
   * @param value the {@code long} value to be queried.
   * @throws IllegalArgumentException if given {@code long} value is not in range.
   */
  public static void requireBetween(final long begin, final long end, final long value) {
    if (value <= begin || value >= end) {
      throw new IllegalArgumentException("value is not between begin and end.");
    }
  }
}
