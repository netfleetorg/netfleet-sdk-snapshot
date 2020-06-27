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

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Enumerator {

  private Enumerator() {
  }

  public static <T> Enumeration<T> enumeration(Collection<T> collection) {
    final Iterator<T> it;
    if (collection == null) {
      it = Collections.emptyIterator();
    } else {
      it = collection.iterator();
    }

    return enumeration(it);
  }

  public static <K, V> Enumeration<K> enumerationKeys(Set<Map.Entry<K, V>> entrySet) {
    LinkedList<K> list = new LinkedList<>();
    for (Map.Entry<K, V> entry : entrySet) {
      list.add(entry.getKey());
    }

    return enumeration(list);
  }

  public static <T> Enumeration<T> enumeration(final Iterator<T> iterator) {
    final Iterator<T> it = iterator;
    return new Enumeration<T>() {

      @Override
      public boolean hasMoreElements() {
        return it.hasNext();
      }

      @Override
      public T nextElement() {
        return it.next();
      }
    };
  }
}
