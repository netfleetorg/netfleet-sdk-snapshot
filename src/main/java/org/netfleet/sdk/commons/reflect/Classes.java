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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Compatibility:
 *
 * <p>
 * {@code java.lang.Class.getEnclosingClass}</p> valid since JDK 1.5. Compatible with JDK7.
 *
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line
 * 1089</a>
 *
 * <p>
 * {@code java.lang.Class.isInstance}</p> valid since JDK 1.1. Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line
 * 413</a>
 * @since 1.0.0-RELEASE
 */
public final class Classes {

  private Classes() {
  }

  public static boolean is(Class<?> source, Class<?> target) {
    if (source == null && target == null) {
      return true;
    } else if (source == null || target == null) {
      return false;
    }

    return source.equals(target);
  }

  public static boolean is(Class<?> source, Field target) {
    if (source == null && target == null) {
      return true;
    } else if (source == null || target == null) {
      return false;
    }

    return source.equals(target.getType());
  }

  public static boolean isInner(Class<?> klass) {
    if (klass == null) {
      return false;
    }

    final Class<?> enclose = klass.getEnclosingClass();

    return enclose != null;
  }

  public static Collection<Class<?>> getAllInterfaces(Class<?> klass) {
    if (klass == null) {
      Collections.emptySet();
    }

    final Set<Class<?>> stack = new HashSet<>();
    Class<?> tmpClass = klass;

    while (tmpClass != null) {
      Class<?>[] interfaces = tmpClass.getInterfaces();
      if (interfaces != null) {
        for (Class<?> each : interfaces) {
          stack.add(each);
        }
      }

      tmpClass = tmpClass.getSuperclass();
    }

    return stack;
  }

  public static Collection<Class<?>> getAllSuperClasses(Class<?> klass) {
    if (klass == null) {
      Collections.emptyList();
    }

    final List<Class<?>> stack = new ArrayList<>();
    Class<?> tmpClass = klass;

    while (tmpClass != null) {
      stack.add(tmpClass);
      tmpClass = tmpClass.getSuperclass();
    }

    return stack;
  }

  public static boolean instanceOf(final Object object, final Class<?> klas) {
    if ((object == null) || (klas == null)) {
      return false;
    }

    return klas.isInstance(object);
  }
}
