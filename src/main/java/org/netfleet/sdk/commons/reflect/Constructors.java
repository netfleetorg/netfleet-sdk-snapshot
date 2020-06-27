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

import org.netfleet.sdk.util.Objects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Compatibility:
 *
 * <p>
 * {@code java.lang.Class.getDeclaredConstructors}</p> valid since JDK 1.1. Compatible with JDK7,
 * JDK8.
 *
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @author Nazım Bahadır Bardakçı - bahadirbardakci [at] terrayazilim [dot] com [dot] tr
 * @version 1.0.0-RELEASE
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line
 * 1848</a>
 *
 * <p>
 * {@code java.lang.reflect.Constructor.isAccesible}</p> method comes from AccessibleObject.java and
 * It's valid since JDK 1.2 Compatible with >= JDK7
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/AccessibleObject.java">Line
 * 151</a>
 *
 * <p>
 * {@code java.lang.reflect.Constructor.getParameterTypes}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Constructor.java">Line
 * 210</a>
 *
 * <p>
 * {@code java.lang.reflect.Constructor.newInstance}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Constructor.java">Line
 * 508</a>
 * @since 1.0.0-RELEASE
 */
public final class Constructors {

  final static Object NULL = null;

  private Constructors() {
  }

  static boolean defNull(Object... args) {
    if (args == null) {
      return true;
    } else if (Arrays.equals(args, new Object[0])) {
      return true;
    } else if (args.length == 1 && args[0] == null) {
      return true;
    }

    return false;
  }

  /**
   * <p>
   * If given parameters are equals to one of listed below, then method tries to return default
   * constructor.
   * <p>
   * null, new Object[0], parameters[0].
   *
   * </p>
   *
   * @param klass
   * @param parameters
   * @return An java.lang.reflect.Constructor of given Class and parameters. However, given class
   * has not default constructor, then it return null.
   */
  public static Constructor get(Class<?> klass, Object... parameters) {
    if (klass == null) {
      return null;
    } else if (defNull(parameters)) {
      return Constructors.get(klass, ((Class<?>[]) NULL));
    }

    final int length = parameters.length;
    final Class<?>[] klasses = new Class<?>[length];
    for (int i = 0; i < length; i++) {
      final boolean isClass = parameters[i] instanceof Class<?>;
      klasses[i] = isClass ? ((Class<?>) parameters[i]) : (parameters[i].getClass());
    }

    return Constructors.get(klass, klasses);
  }

  /**
   * @param klass
   * @param parameters
   * @return true if such an java.lang.reflect.Constructor exists of given Class. Otherwise false.
   */
  public static boolean isDeclared(Class<?> klass, Object... parameters) {
    return Objects.nonNull(Constructors.get(klass, parameters));
  }

  /**
   * @param klass
   * @return default java.lang.reflect.Constructor of given Class. However if given Class does not
   * have default constructor then it returns null.
   */
  public static Constructor get(Class<?> klass) {
    return Constructors.get(klass, new Object[0]);
  }

  public static Constructor get(Class<?> klass, Class<?>... parameters) {
    if (klass == null) {
      return null;
    }

    final boolean isDef = defNull(parameters);
    final int argsLen = isDef ? 0 : parameters.length;

    Constructor found = null;
    final Constructor[] constructors = klass.getDeclaredConstructors();
    for (Constructor constructor : constructors) {
      constructor.setAccessible(true);
      final Class[] params = constructor.getParameterTypes();
      final int paramsLen = params.length;
      if (isDef && (paramsLen == 1)) {
        found = constructor;
        break;
      }

      if (paramsLen - 1 == argsLen) {
        boolean isMatched = true;
        for (int i = 0; i < paramsLen - 1; i++) {
          if (!params[i + 1].equals(parameters[i])) {
            isMatched = false;
            break;
          }
        }

        if (isMatched) {
          found = constructor;
          break;
        }
      }
    }

    return found;
  }

  public static Object invoke(Constructor constructor, Object... args) {
    if (constructor == null) {
      return null;
    }

    Object instance;
    final boolean isDef = defNull(args);

    if (isDef) {
      try {
        instance = constructor.newInstance(NULL);
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        return null;
      }
    } else {
      try {
        final Object[] array = new Object[args.length + 1];
        array[0] = null;

        for (int i = 1; i < args.length + 1; i++) {
          array[i] = args[i - 1];
        }

        instance = constructor.newInstance(array);
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        return null;
      }
    }

    return instance;
  }

  public static Object invoke(Class<?> klass) {
    if (klass == null) {
      return null;
    }

    Object instance = null;
    final Constructor constructor = Constructors.get(klass, NULL);
    if (constructor != null) {
      instance = Constructors.invoke(constructor, NULL);
    }

    if (instance == null) {
      return null;
    }

    return instance;
  }
}
