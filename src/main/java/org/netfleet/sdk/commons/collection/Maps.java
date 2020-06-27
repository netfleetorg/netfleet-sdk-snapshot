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

import org.netfleet.sdk.util.Objects;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Maps {

  private Maps() {
  }

  /**
   * @param <K>   generic sign of the key
   * @param <V>   generic sign of the value
   * @param stack the {@link Map} can be {@literal null}.
   * @param value the value can be {@literal null}.
   * @return key of given value. {@literal null} if value does not exists.
   */
  public static <K, V> K getKey(final Map<K, V> stack, V value) {
    if (nullOrEmpty(stack)) {
      return null;
    }

    K key = null;
    for (Map.Entry<K, V> entrySet : stack.entrySet()) {
      K currentKey = entrySet.getKey();
      V currentValue = entrySet.getValue();

      if (Objects.equals(value, currentValue)) {
        key = currentKey;
        break;
      }
    }

    return key;
  }

  /**
   * @param <K>   generic sign of the key
   * @param <V>   generic sign of the value
   * @param stack the {@link Map} can be {@literal null}.
   * @return a {@link Collections#unmodifiableMap(java.util.Map)} from given
   * {@link Map}. Empty {@link Map} if given {@link Map} is {@literal null}.
   */
  public static <K, V> Map<K, V> toImmutable(final Map<K, V> stack) {
    if (nullOrEmpty(stack)) {
      final Map<K, V> emptyMap = Collections.emptyMap();
      return Collections.unmodifiableMap(emptyMap);
    }

    return Collections.unmodifiableMap(stack);
  }

  public static <K, V> Map<K, V> deepCopy(final Map<K, V> source, Map<K, V> stack) throws IOException, ClassNotFoundException {
    if (nullOrEmpty(source)) {
      final Map<K, V> emptyMap = Collections.emptyMap();
      return Collections.unmodifiableMap(emptyMap);
    }

    if (stack == null) {
      stack = new HashMap<>();
    }

    for (Map.Entry<K, V> entrySet : source.entrySet()) {
      K key = Objects.deepCopy(entrySet.getKey());
      V value = Objects.deepCopy(entrySet.getValue());

      stack.put(key, value);
    }

    return stack;
  }

  public static <K, V> Map<K, V> deepCopy(final Map<K, V> source) throws IOException, ClassNotFoundException {
    return deepCopy(source, null);
  }

  public static <K, V> boolean nullOrEmpty(final Map<K, V> stack) {
    return (stack == null) || stack.isEmpty();
  }

  public static <K, V> Map<K, V> getIfNull(final Map<K, V> nullable, final Map<K, V> map) {
    if (nullable == null) {
      return map;
    }

    return nullable;
  }

}
