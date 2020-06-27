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

import org.netfleet.sdk.annotation.Mutable;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@Mutable
public final class WeakCache<K, V> {

  private final Map<K, Reference<V>> cache = new WeakHashMap<>();

  public V get(final K key) {
    Reference<V> reference = this.cache.get(key);
    if (reference == null) {
      return null;
    }
    V value = reference.get();
    if (value == null) {
      this.cache.remove(key);
    }
    return value;
  }

  public void put(final K key, final V value) {
    if (value != null) {
      this.cache.put(key, new WeakReference<>(value));
    } else {
      this.cache.remove(key);
    }
  }

  /**
   * @param key   the key of the entry to be added.
   * @param value the value of the entry to added.
   * @return the value which associated with key.
   * @see ConcurrentHashMap#putIfAbsent(java.lang.Object, java.lang.Object)
   */
  public V putIfAbsent(final K key, final V value) {
    if (cache.containsKey(key)) {
      Reference<V> ref = cache.get(key);
      if (ref != null) {
        return ref.get();
      } else {
        return null;
      }
    } else {
      return cache.put(key, new WeakReference<>(value)).get();
    }
  }

  public void clear() {
    this.cache.clear();
  }
}
