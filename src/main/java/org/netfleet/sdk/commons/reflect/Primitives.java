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

import org.netfleet.sdk.util.Chars;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Compatibility:
 *
 * <p>
 * {@code java.lang.reflect.ParameterizedType}</p> valid since JDK 1.5. Compatible with JDK7.
 *
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @author Nazım Bahadır Bardakçı - bahadirbardakci [at] terrayazilim [dot] com [dot] tr
 * @version 1.0.0-RELEASE
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/ParameterizedType.java"></a>
 *
 * <p>
 * {@code java.lang.reflect.ParameterizedType.getActualTypeArguments}</p> valid since JDK 1.5.
 * Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/ParameterizedType.java ">Line
 * 65</a>
 *
 * <p>
 * {@code java.lang.reflect.Field.getGenericType}</p> valid since JDK 1.5. Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/reflect/Field.java">Line
 * 234</a>
 *
 * <p>
 * {@code java.lang.Class.getComponentType}</p> valid since JDK 1.1. Compatible with JDK7.
 * @see <a href="http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/lang/Class.java">Line
 * 830</a>
 * @since 1.0.0-RELEASE
 */
public final class Primitives {

  private Primitives() {
  }

  static final Map<Class<?>, Class<?>> primitive2wrapper;
  static final Map<Class<?>, Class<?>> wrapper2primitive;
  static final Set<Class<?>> numberPrimitives;

  static {
    Map<Class<?>, Class<?>> pr2wr = new HashMap<>();
    Map<Class<?>, Class<?>> wr2pr = new HashMap<>();
    Set<Class<?>> np = new HashSet<>();

    map(pr2wr, wr2pr, int.class, Integer.class);
    map(pr2wr, wr2pr, double.class, Double.class);
    map(pr2wr, wr2pr, long.class, Long.class);
    map(pr2wr, wr2pr, float.class, Float.class);
    map(pr2wr, wr2pr, char.class, Character.class);
    map(pr2wr, wr2pr, boolean.class, Boolean.class);
    map(pr2wr, wr2pr, void.class, Void.class);
    map(pr2wr, wr2pr, short.class, Short.class);
    map(pr2wr, wr2pr, byte.class, Byte.class);
    map(pr2wr, wr2pr, String.class, String.class);
    np.add(int.class);
    np.add(double.class);
    np.add(long.class);
    np.add(float.class);
    np.add(short.class);
    np.add(byte.class);

    numberPrimitives = Collections.unmodifiableSet(np);
    primitive2wrapper = Collections.unmodifiableMap(pr2wr);
    wrapper2primitive = Collections.unmodifiableMap(wr2pr);
  }

  static void map(Map pr2wr, Map wr2pr, Class<?> p, Class<?> w) {
    pr2wr.put(p, w);
    wr2pr.put(w, p);
  }

  public static boolean isAssignable(Class<?> type, Class<?> primType) {
    return new Resolver<>(type).isPermittedType(primType);
  }

  public static <T> T resolve(Class<T> type, Object wrapper) {
    return new Resolver<>(type, wrapper).resolve();
  }

  static public class Resolver<T> {

    final Class<T> type;
    final Object wrapper;
    final Map<Class<?>, Set<Class<?>>> permitted;

    public Resolver(Class<T> type, Object wrapper) {
      this.type = type;
      this.wrapper = wrapper;
      this.permitted = new HashMap<>();

      permitted.put(Integer.TYPE, integerPolicy);
      permitted.put(Double.TYPE, doublePolicy);
      permitted.put(Float.TYPE, floatPolicy);
      permitted.put(Long.TYPE, longPolicy);
      permitted.put(Short.TYPE, shortPolicy);
      permitted.put(Byte.TYPE, bytePolicy);
      permitted.put(Character.TYPE, charPolicy);
      permitted.put(Boolean.TYPE, boolPolicy);
    }

    public Resolver(Class<T> type) {
      this(type, null);
    }

    public T resolve() {
      if (!isPermittedType(getWrapper().getClass())) {
        return null;
      }

      final Class<?> srcType = Primitives.toPrimitive(getType());
      final Class<?> tarType = Primitives.toPrimitive(getWrapper().getClass());

      if (!Primitives.isPrimitive(srcType) || !Primitives.isPrimitive(tarType)) {
        return null;
      }

      if (Primitives.isNumber(getType()) && Primitives.isNumber(getWrapper().getClass())) {
        Number numberType = (Number) getWrapper();
        return resolve(numberType);
      } else if (srcType.equals(Character.TYPE)) {
        if (tarType.equals(Integer.TYPE)) {
          Integer integerType = (Integer) getWrapper();
          return (T) Chars.from(integerType);
        } else if (tarType.equals(Short.TYPE)) {
          Short shortType = (Short) getWrapper();
          return (T) Chars.from(shortType);
        }
      } else if (srcType.equals(Boolean.TYPE) && tarType.equals(Boolean.TYPE)) {
        return (T) getWrapper();
      }

      return null;
    }

    T resolve(Number numberType) {
      if (Primitives.toPrimitive(getType()).equals(Integer.TYPE)) {
        return (T) ((Integer) numberType.intValue());
      } else if (Primitives.toPrimitive(getType()).equals(Double.TYPE)) {
        return (T) ((Double) numberType.doubleValue());
      } else if (Primitives.toPrimitive(getType()).equals(Float.TYPE)) {
        return (T) ((Float) numberType.floatValue());
      } else if (Primitives.toPrimitive(getType()).equals(Long.TYPE)) {
        return (T) ((Long) numberType.longValue());
      } else if (Primitives.toPrimitive(getType()).equals(Short.TYPE)) {
        return (T) ((Short) numberType.shortValue());
      } else if (Primitives.toPrimitive(getType()).equals(Byte.TYPE)) {
        return (T) ((Byte) numberType.byteValue());
      }

      throw new RuntimeException("This should never happen.");
    }

    boolean isPermittedType(Class<?> type) {
      if (getPermitted() == null) {
        return false;
      }

      Class<?> src = Primitives.toPrimitive(getType());
      Class<?> tar = Primitives.toPrimitive(type);

      return getPermitted().get(src).contains(tar);
    }

    Class<T> getType() {
      return type;
    }

    Object getWrapper() {
      return wrapper;
    }

    Map<Class<?>, Set<Class<?>>> getPermitted() {
      return permitted;
    }
  }

  static final Set<Class<?>> integerPolicy = new HashSet<Class<?>>() {
    {
      add(Integer.TYPE);
      add(Double.TYPE);
      add(Float.TYPE);
      add(Long.TYPE);
      add(Short.TYPE);
      add(Byte.TYPE);
    }
  };

  static final Set<Class<?>> doublePolicy = new HashSet<Class<?>>() {
    {
      add(Integer.TYPE);
      add(Double.TYPE);
      add(Float.TYPE);
      add(Long.TYPE);
    }
  };

  static final Set<Class<?>> floatPolicy = new HashSet<Class<?>>() {
    {
      add(Integer.TYPE);
      add(Double.TYPE);
      add(Float.TYPE);
    }
  };

  static final Set<Class<?>> longPolicy = new HashSet<Class<?>>() {
    {
      add(Integer.TYPE);
      add(Double.TYPE);
      add(Float.TYPE);
      add(Long.TYPE);
    }
  };

  static final Set<Class<?>> shortPolicy = new HashSet<Class<?>>() {
    {
      add(Integer.TYPE);
      add(Double.TYPE);
      add(Float.TYPE);
      add(Short.TYPE);
      add(Byte.TYPE);
    }
  };

  static final Set<Class<?>> bytePolicy = new HashSet<Class<?>>() {
    {
      add(Integer.TYPE);
      add(Short.TYPE);
      add(Byte.TYPE);
    }
  };

  static final Set<Class<?>> charPolicy = new HashSet<Class<?>>() {
    {
      add(Integer.TYPE);
      add(Short.TYPE);
    }
  };

  static final Set<Class<?>> boolPolicy = new HashSet<Class<?>>() {
    {
      add(Boolean.TYPE);
    }
  };

  public static <T> boolean isPrimitive(Class<T> type) {
    return primitive2wrapper.get(type) != null;
  }

  public static <T> boolean isWrapper(Class<T> type) {
    return wrapper2primitive.get(type) != null;
  }

  public static <T> boolean either(Class<T> type) {
    return (primitive2wrapper.get(type) != null)
      || (wrapper2primitive.get(type) != null);
  }

  public static <T> boolean neither(Class<T> type) {
    return (primitive2wrapper.get(type) == null)
      && (wrapper2primitive.get(type) == null);
  }

  public static <T> Class<T> toWrapper(Class<T> type) {
    Class<T> wrapped = (Class<T>) primitive2wrapper.get(type);

    return (wrapped == null) ? type : wrapped;
  }

  public static <T> Class<T> toPrimitive(Class<T> type) {
    Class<T> wrapped = (Class<T>) wrapper2primitive.get(type);

    return (wrapped == null) ? type : wrapped;
  }

  public static <T> boolean isNumber(Class<T> type) {
    if (Number.class.isAssignableFrom(type)) {
      return true;
    }

    Class<?> numberType = toPrimitive(type);
    return numberPrimitives.contains(numberType);
  }
}
