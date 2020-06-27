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

import org.netfleet.sdk.commons.reflect.Classes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Objects {

  private Objects() {
  }

  /**
   * @param <T>    the generic sign of the given {@link Object}.
   * @param object the {@link Object} to be deep copied, can be {@literal null}.
   * @return T if deep copy succeed.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public static <T> T deepCopy(T object) throws IOException, ClassNotFoundException {
    if (object == null) {
      return null;
    }

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);

    oos.writeObject(object);
    oos.flush();
    oos.close();
    bos.close();

    byte[] byteData = bos.toByteArray();
    ByteArrayInputStream bais = new ByteArrayInputStream(byteData);

    return (T) new ObjectInputStream(bais).readObject();
  }

  /**
   * @param object the {@link Object}
   * @return {@code true} if given {@link Object} is not {@literal null}.
   * Otherwise {@code false}.
   */
  public static boolean nonNull(final Object object) {
    return object != null;
  }

  /**
   * @param object the {@link Object}
   * @return {@code true} if given {@link Object} is {@literal null}.
   * Otherwise {@code false}.
   */
  public static boolean isNull(final Object object) {
    return object == null;
  }

  /**
   * @param object the {@link Object}
   * @return {@code true} if given {@link Object} is instance of array.
   * Otherwise {@code false}.
   */
  public static boolean isArray(final Object object) {
    return (object != null) && object.getClass().isArray();
  }

  /**
   * @param <T>     the generic sign of the given first {@link Object}.
   * @param <U>     the generic sign of the given second {@link Object}.
   * @param object0 the {@link Object}, can be {@literal null}.
   * @param object1 the {@link Object}, can be {@literal null}.
   * @return {@code true} if given arguments are equal. Otherwise {@code false}.
   * @since 1.0.3
   */
  public static <T, U> boolean equals(final T object0, final U object1) {
    if ((object0 == null) && (object1 == null)) {
      return true;
    } else if ((object0 == null) || (object1 == null)) {
      return false;
    }

    if (isArray(object0) && isArray(object1)) {
      final Class<?> c0 = object0.getClass().getComponentType();
      final Class<?> c1 = object1.getClass().getComponentType();

      if (c0.isPrimitive() && c1.isPrimitive()) {
        if (!c0.equals(c1)) {
          return false;
        } else if (Classes.is(c0, int.class)) {
          return Arrays.equals(((int[]) object0), ((int[]) object1));
        } else if (Classes.is(c0, double.class)) {
          return Arrays.equals(((double[]) object0), ((double[]) object1));
        } else if (Classes.is(c0, float.class)) {
          return Arrays.equals(((float[]) object0), ((float[]) object1));
        } else if (Classes.is(c0, long.class)) {
          return Arrays.equals(((long[]) object0), ((long[]) object1));
        } else if (Classes.is(c0, boolean.class)) {
          return Arrays.equals(((boolean[]) object0), ((boolean[]) object1));
        } else if (Classes.is(c0, byte.class)) {
          return Arrays.equals(((byte[]) object0), ((byte[]) object1));
        } else if (Classes.is(c0, char.class)) {
          return Arrays.equals(((char[]) object0), ((char[]) object1));
        } else if (Classes.is(c0, short.class)) {
          return Arrays.equals(((short[]) object0), ((short[]) object1));
        }
      } else {
        return Arrays.equals(((T[]) object0), ((U[]) object1));
      }
    }

    return object0.equals(object1);
  }

  /**
   * @param <T>    the generic sign of the given {@link Object}.
   * @param array  the array to be searched, can be {@literal null}.
   * @param object the element to be searched for, cannot be {@literal null}.
   * @return {@code true} if given element contained in the given array.
   * Otherwise {@code false}.
   */
  public static <T> boolean contains(final T[] array, final T object) {
    if (!isNull(array) && !isNull(object)) {
      for (T each : array) {
        if (each.equals(object)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * @param <T>    the generic sign of the given {@link Object}.
   * @param array  the array to be searched, can be {@literal null}.
   * @param object the element to be searched for, cannot be {@literal null}.
   * @return Index of given element in the given array. {@code -1} if something
   * goes wrong.
   */
  public static <T> int indexOf(final T[] array, final T object) {
    if (!isNull(array) && !isNull(object)) {
      final int length = array.length;
      for (int t = 0; t < length; t++) {
        if (array[t].equals(object)) {
          return t;
        }
      }
    }

    return -1;
  }

  /**
   * @param array   the array to be added, cannot be {@literal null}.
   * @param element the element to be added to the array, can be {@literal null}.
   * @return A new array created with given array plus given element.
   */
  public static Object[] add(final Object[] array, final Object element) {
    final int length = array.length;
    final Object[] newArray = new Object[length + 1];

    System.arraycopy(array, 0, newArray, 0, length);

    newArray[length] = element;
    return newArray;
  }

  /**
   * @param array the array to be searched, can be {@literal null}.
   * @return {@code true} if given array is either {@literal null} or empty.
   * Otherwise {@code false}.
   * @since 1.0.3
   */
  public static boolean isEmpty(final Object array) {
    if (!isArray(array)) {
      return false;
    }

    return Array.getLength(array) == 0;
  }

  /**
   * @param array the array to be searched, can be {@literal null}.
   * @param index the index to be removed.
   * @return A new array created from given array minus element that specified
   * with given index.
   * @since 1.0.3
   */
  public static <Q> Q[] remove(final Q[] array, final int index) {
    if (array == null) {
      return null;
    } else if (index < 0) {
      return array;
    }

    final int length = array.length;
    if (index > length) {
      return null;
    }

    final Class<?> genericType = array.getClass().getComponentType();
    final Q[] newArray = (Q[]) Array.newInstance(genericType, length - 1);

    System.arraycopy(array, 0, newArray, 0, index);
    if (index > length - 1) {
      return newArray;
    }

    final int destination = length - index - 1;
    System.arraycopy(array, index + 1, newArray, index, destination);

    return newArray;
  }

  /**
   * @param <T>     the generic sign of the given {@link Object}.
   * @param array   the array to be searched, can be {@literal null}.
   * @param element the element to be removed. can be {@literal null}.
   * @return A new array created from given array minus given element.
   */
  public static <T> T[] remove(final T[] array, T element) {
    final int idx = indexOf(array, element);
    if (idx == -1) {
      return null;
    }

    return remove(array, idx);
  }

  /**
   * @param array
   * @param rejected
   * @return
   * @since 1.0.3
   */
  public static Object[] filter(final Object array, final Object rejected) {
    if (array == null) {
      return null;
    } else if (!isArray(array)) {
      return null;
    }

    final Object[] elements = (Object[]) array;
    final List<Object> stack = new ArrayList<>();
    final int length = elements.length;

    for (int i = 0; i < length; i++) {
      final Object adhoc = elements[i];

      if (!equals(adhoc, rejected)) {
        stack.add(adhoc);
      }
    }

    return stack.toArray();
  }

  /**
   * @param <Q>
   * @param reference
   * @param length
   * @return
   */
  public static <Q> Q[] toArray(final Class<?> reference, final int length) {
    return (Q[]) Array.newInstance(reference, length);
  }

  /**
   * @return
   * @since 1.0.3
   */
  public static Object[] toArray() {
    return (Object[]) Array.newInstance(Object.class, 0);
  }

  /**
   * @param <Q>
   * @param type
   * @return
   * @since 1.0.3
   */
  public static <Q> Q[] toArray(Class<?> type) {
    return (Q[]) Array.newInstance(type, 0);
  }

  /**
   * @param object Object
   * @return
   */
  public static long toLong(final Object object) {
    if (object == null) {
      return 0l;
    }

    Class<?> classType = object.getClass();
    if (classType.getSuperclass().equals(Number.class)) {
      final Number theNumber = (Number) object;
      return theNumber.longValue();
    } else if (object instanceof String) {
      return Long.valueOf(((String) object));
    }

    throw new IllegalArgumentException("Invalid arg");
  }

  /**
   * @param object Object
   * @return float
   */
  public static float toFloat(final Object object) {
    if (object == null) {
      return 0f;
    }

    Class<?> classType = object.getClass();
    if (classType.getSuperclass().equals(Number.class)) {
      final Number theNumber = (Number) object;
      return theNumber.floatValue();
    } else if (object instanceof String) {
      return Float.valueOf(((String) object));
    }

    throw new IllegalArgumentException("Invalid arg");
  }

  /**
   * @param object Object
   * @return double
   */
  public static double toDouble(final Object object) {
    if (object == null) {
      return 0d;
    }

    Class<?> classType = object.getClass();
    if (classType.getSuperclass().equals(Number.class)) {
      final Number theNumber = (Number) object;
      return theNumber.doubleValue();
    } else if (object instanceof String) {
      return Double.valueOf(((String) object));
    }

    throw new IllegalArgumentException("Invalid arg");
  }

  /**
   * @param object Object
   * @return
   */
  public static int toInt(final Object object) {
    if (object == null) {
      return 0;
    }

    Class<?> classType = object.getClass();
    if (classType.getSuperclass().equals(Number.class)) {
      final Number theNumber = (Number) object;
      return theNumber.intValue();
    } else if (object instanceof String) {
      return Integer.valueOf(((String) object));
    }

    throw new IllegalArgumentException("Invalid arg");
  }

  /**
   * @param object Object
   * @return true if given Object is instance of Number. Otherwise false.
   */
  public static boolean isNumber(Object object) {
    return object.getClass().getSuperclass().equals(Number.class);
  }

  /**
   * @param object Object
   * @return
   */
  public static boolean toBool(Object object) {
    boolean bool;
    if (object instanceof Boolean) {
      bool = ((boolean) object);
    } else if (object instanceof String) {
      bool = Boolean.valueOf(((String) object));
    } else {
      throw new IllegalArgumentException("Invalid arg");
    }

    return bool;
  }

  /**
   * @param <Q>
   * @param nullable
   * @param object
   * @return
   * @since 1.0.3
   */
  public static <Q> Q getIfNull(Q nullable, Q object) {
    if (nullable == null) {
      return object;
    }

    return nullable;
  }

}