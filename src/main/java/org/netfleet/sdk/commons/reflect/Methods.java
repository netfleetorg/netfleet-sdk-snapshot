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
package org.netfleet.sdk.commons.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.netfleet.sdk.util.Strings;

/**
 * Compatibility:
 *
 * <p>
 * {@code java.lang.reflect.Method.isAccesible}</p> method comes from AccessibleObject.java and It's
 * valid since JDK 1.2 Compatible with >= JDK7
 *
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @author Nazım Bahadır Bardakçı - bahadirbardakci [at] terrayazilim [dot] com [dot] tr
 * @version 1.0.0-RELEASE
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/AccessibleObject.java">Line
 * 151</a>
 *
 * <p>
 * {@code java.lang.reflect.Method.invoke}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Method.java">Line
 * 586</a>
 *
 * <p>
 * {@code java.lang.reflect.Method.getName}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Method.java">Line
 * 171</a>
 *
 * <p>
 * {@code java.lang.reflect.Field.get}</p> comes from {@link sun.reflect.FieldAccessor}. Compatible
 * with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/sun/reflect/FieldAccessor.java"></a>
 *
 * <p>
 * {@code java.lang.Class.getMethods}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line
 * 1422</a>
 *
 * <p>
 * {@code java.lang.Class.getMethod}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line
 * 1614.</a>
 * @since 1.0.0-RELEASE
 */
public final class Methods {

  static final Object NULL = null;

  private Methods() {
  }

  public static Object invoke(Object instance, String methodName, Object... parameters) {
    if (instance == null || Strings.nullOrBlank(methodName)) {
      return null;
    }

    Method method = get(instance.getClass(), methodName);
    if (method == null) {
      return null;
    }

    if (!method.isAccessible()) {
      method.setAccessible(true);
    }

    Object value;

    try {
      value = method.invoke(instance, parameters);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      return null;
    }

    return value;
  }

  public static Object invoke(Object instance, Method method, Object... parameters) {
    if (instance == null || method == null) {
      return null;
    }

    if (!method.isAccessible()) {
      method.setAccessible(true);
    }

    Object value;

    try {
      value = method.invoke(instance, parameters);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      return null;
    }

    return value;
  }

  public static Method getGetter(final Class<?> klass, final Field field, boolean reactOnBool) {
    if (klass == null || field == null) {
      return null;
    }

    Method[] methods = klass.getMethods();
    Method method = null;
    String prefix = "get";
    int offset = prefix.length();

    if (reactOnBool && Classes.is(boolean.class, field)) {
      prefix = "is";
      offset = prefix.length();
    }

    for (Method eachMethod : methods) {
      if (!eachMethod.isAccessible()) {
        eachMethod.setAccessible(true);
      }

      final boolean isGetter = eachMethod.getName().startsWith(prefix);
      if (!isGetter) {
        continue;
      }

      final int k = eachMethod.getName().length();
      final int l = field.getName().length() + offset;
      final boolean heuristic = Integer.compare(k, l) == 0;
      if (!heuristic) {
        continue;
      }

      final String m = eachMethod.getName().toLowerCase(Locale.ENGLISH);
      final String n = field.getName().toLowerCase(Locale.ENGLISH);

      if (m.endsWith(n)) {
        method = eachMethod;
        break;
      }
    }

    return method;
  }

  public static Method getGetter(final Object instance, final Field field, boolean reactOnBool) {
    if (instance == null) {
      return null;
    }

    return getGetter(instance.getClass(), field, reactOnBool);
  }

  public static Method getGetter(final Class<?> klass, final String fieldName, boolean reactOnBool) {
    if (klass == null || Strings.nullOrBlank(fieldName)) {
      return null;
    }

    Field field = Fields.get(klass, fieldName);
    if (field == null) {
      return null;
    }

    return getGetter(klass, field, reactOnBool);
  }

  /**
   * @param instance
   * @param fieldName
   * @param reactOnBool
   * @return
   */
  public static Method getGetter(final Object instance, final String fieldName, boolean reactOnBool) {
    if (instance == null) {
      return null;
    }

    return getGetter(instance.getClass(), fieldName, reactOnBool);
  }

  public static Method getSetter(Class<?> klass, Field field, boolean reactOnBool) {
    if (klass == null || field == null) {
      return null;
    }

    Method[] methods = klass.getMethods();
    Method method = null;
    String prefix = "set";
    int offset = prefix.length();

    if (reactOnBool && Classes.is(boolean.class, field)) {
      prefix = prefix + "Is";
      offset = prefix.length();
    }

    for (Method eachMethod : methods) {
      if (!eachMethod.isAccessible()) {
        eachMethod.setAccessible(true);
      }

      if (!eachMethod.getName().startsWith(prefix)) {
        continue;
      }

      final int k = eachMethod.getName().length();
      final int l = field.getName().length() + offset;
      if (Integer.compare(k, l) != 0) {
        continue;
      }

      final String m = eachMethod.getName().toLowerCase(Locale.ENGLISH);
      final String n = field.getName().toLowerCase(Locale.ENGLISH);

      if (m.endsWith(n)) {
        method = eachMethod;
        break;
      }
    }

    return method;
  }

  public static Method getSetter(Object instance, Field field, boolean reactOnBool) {
    if (instance == null) {
      return null;
    }

    return getSetter(instance.getClass(), field, reactOnBool);
  }

  public static Method getSetter(Object instance, String fieldName, boolean reactOnBool) {
    if (instance == null) {
      return null;
    }

    return getSetter(instance.getClass(), fieldName, reactOnBool);
  }

  public static Method getSetter(Class<?> klass, String fieldName, boolean reactOnBool) {
    if (klass == null || Strings.nullOrBlank(fieldName)) {
      return null;
    }

    Field field = Fields.get(klass, fieldName);
    if (field == null) {
      return null;
    }

    return getSetter(klass, field, reactOnBool);
  }

  public static Method get(Class<?> klass, String methodName) {
    if (klass == null || Strings.nullOrBlank(methodName)) {
      return null;
    }

    final Method[] methods = klass.getMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        return method;
      }
    }

    return null;
  }

  public static Method get(Object instance, String methodName) {
    if (instance == null) {
      return null;
    }

    return get(instance.getClass(), methodName);
  }
}
