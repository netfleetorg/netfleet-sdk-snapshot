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
import java.util.LinkedList;

import org.netfleet.sdk.util.Ints;
import org.netfleet.sdk.util.Objects;
import org.netfleet.sdk.util.Strings;

/**
 * Compatibility:
 *
 * <p>{@code java.lang.Class.getDeclaredField}</p> valid since JDK 1.1. Compatible with JDK7.
 *
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @author Nazım Bahadır Bardakçı - bahadirbardakci [at] terrayazilim [dot] com [dot] tr
 * @version 1.0.0-RELEASE
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line 1891</a>
 *
 * <p>{@code java.lang.Class.getDeclaredFields}</p> valid since JDK 1.1. Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line 1755</a>
 *
 * <p>{@code java.lang.reflect.Field.isAccesible}</p> method comes from AccessibleObject.java and
 * It's valid since JDK 1.2 Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/AccessibleObject.java">Line 151</a>
 *
 * <p>{@code java.lang.reflect.Field.get}</p> comes from {@link sun.reflect.FieldAccessor}. Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/sun/reflect/FieldAccessor.java">OpenJDK Source</a>
 *
 * <p>{@code java.lang.reflect.Field.set}</p> connected with {@link sun.reflect.FieldAccessor} Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Field.java">OpenJDK Source</a>
 *
 * <p>{@code java.lang.reflect.Field.getType}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Field.java">Line 206</a>
 *
 * <p>{@code java.lang.Class.isAssignableFrom}</p> valid since JDK 1.1. Compatible with JDK7, JDK8.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line 441</a>
 *
 * <p>{@code java.lang.reflect.Field.getType}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Field.java">Line 170</a>
 *
 * <p>{@code java.lang.reflect.Field.getName}</p> Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Field.java">Line 159</a>
 *
 * <p>{@code java.lang.reflect.Field.isSynthetic}</p> valid since JDK 1.5. Compatible with JDK7.
 * @see http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Field.java Line 194.
 * @since 1.0.0-RELEASE
 */
public final class Fields {

  private Fields() {
  }

  static Field get(final Class<?> instanceClass, final String fieldName, boolean checked) {
    if (!checked) {
      if (instanceClass == null) {
        return null;
      } else if (Strings.nullOrBlank(fieldName)) {
        return null;
      }
    }

    final Field field;

    try {
      field = instanceClass.getDeclaredField(fieldName);
    } catch (NoSuchFieldException | SecurityException ex) {
      return null;
    }

    if (field == null) {
      return null;
    }

    return field;
  }

  public static Field get(final Class<?> instanceClass, final String fieldName) {
    return get(instanceClass, fieldName, false);
  }

  public static Field get(final Object instance, final String fieldName) {
    if (instance == null) {
      return null;
    } else if (instance instanceof Class<?>) {
      return get(((Class<?>) instance), fieldName, true);
    } else if (Strings.nullOrBlank(fieldName)) {
      return null;
    }

    return get(instance.getClass(), fieldName, true);
  }

  public static String[] getFieldNames(final Object instance) {
    if (Objects.isNull(instance)) {
      return Objects.toArray(Strings.class, 0);
    }

    Class<?> klass = instance.getClass();
    Field[] fields = klass.getDeclaredFields();
    int length = fields.length;

    if (length == 0) {
      return Objects.toArray(Strings.class, 0);
    }

    String[] names = new String[length];
    for (int i = 0; i < length; i += 1) {
      final String adhoc = fields[i].getName();
      names[i] = adhoc;
    }

    // I just want the bread and bologna bundles to tuck away,
    // I don't work for free, I am barely giving a fuck away.
    final int index = Objects.indexOf(names, "this$0");
    if (index != -1) {
      names = Objects.remove(names, index);
    }

    return names;
  }

  public static Object get(Object instance, Field field) {
    if (instance == null || field == null) {
      return null;
    }

    final Object value;
    if (!field.isAccessible()) {
      field.setAccessible(true);
    }

    try {
      value = field.get(instance);
    } catch (IllegalArgumentException | IllegalAccessException ex) {
      return null;
    }

    return value;
  }

  public static Field set(Field field, Object instance, Object value) {
    if (instance == null) {
      return null;
    }

    try {
      field.setAccessible(true);
      field.set(instance, value);
    } catch (IllegalArgumentException | IllegalAccessException ex) {
      return null;
    }

    return field;
  }

  public static Collection<String> getFieldNames(Class<?> klass) {
    return getFieldNames(klass, null);
  }

  public static Collection<String> getFieldNames(Object instance, int[] mod) {
    if (instance == null) {
      return Collections.emptySet();
    }

    return getFieldNames(instance.getClass(), mod);
  }

  /**
   * @param klass
   * @param mod
   * @return
   */
  public static Collection<String> getFieldNames(Class<?> klass, int[] mod) {
    return getFieldNames(klass, mod, new HashSet<String>());
  }

  public static Collection<String> getFieldNames(Class<?> klass, int[] mod, Collection<String> stack) {
    if (klass == null) {
      return stack;
    }

    final Field[] fields = klass.getDeclaredFields();
    final int length = fields.length;
    if (length == 0) {
      return stack;
    }

    final Collection<String> names = new LinkedList<>();
    final boolean modsafe = mod != null;
    for (Field field : fields) {
      if (field.getName().equals("this$0")) {
        continue;
      }

      if (modsafe && !Ints.contains(mod, field.getModifiers())) {
        continue;
      }

      final String name = field.getName();
      names.add(name);
    }

    return names;
  }

  public static Collection<String> getAllFieldNames(Class<?> klass, int[] mod) {
    if (klass == null) {
      return Collections.emptyList();
    }

    Field[] fields = klass.getDeclaredFields();
    final int length = fields.length;
    if (length == 0) {
      return Collections.emptyList();
    }

    final boolean modsafe = mod != null;
    final Collection<String> names = new ArrayList<>();
    while (klass != null && klass != Object.class) {
      for (Field field : klass.getDeclaredFields()) {
        if (field.getName().equals("this$0")) {
          continue;
        }

        if (modsafe) {
          if (!Ints.contains(mod, field.getModifiers())) {
            continue;
          }
        }

        final String name = field.getName();
        if (!field.isSynthetic()) {
          names.add(name);
        }
      }

      klass = klass.getSuperclass();
    }

    return names;
  }

  public static Collection<String> getAllFieldNames(Class<?> klass) {
    return getAllFieldNames(klass, null);
  }

  public static Collection<String> getAllFieldNames(Object instance) {
    if (instance == null) {
      return Collections.emptyList();
    }

    return getAllFieldNames(instance.getClass(), null);
  }
}
