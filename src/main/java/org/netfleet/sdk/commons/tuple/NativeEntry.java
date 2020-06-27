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
package org.netfleet.sdk.commons.tuple;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class NativeEntry<K, V> implements Map.Entry<K, V>, Serializable {

  private final K key;
  private V value;

  public NativeEntry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public static <K, V> NativeEntry<K, V> of(K key, V value) {
    return new NativeEntry<>(key, value);
  }

  @Override
  public K getKey() {
    return this.key;
  }

  @Override
  public V getValue() {
    return this.value;
  }

  @Override
  public V setValue(V value) {
    V reference = this.value;
    this.value = value;

    return reference;
  }

  @Override
  public NativeEntry<K, V> clone() {
    return new NativeEntry<>(getKey(), getValue());
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + Objects.hashCode(this.key);
    hash = 47 * hash + Objects.hashCode(this.value);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NativeEntry<?, ?> other = (NativeEntry<?, ?>) obj;
    if (!Objects.equals(this.key, other.key)) {
      return false;
    }
    if (!Objects.equals(this.value, other.value)) {
      return false;
    }
    return true;
  }

}
