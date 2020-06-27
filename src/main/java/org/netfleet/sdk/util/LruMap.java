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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation is access order. So respect following statements.
 * <p>
 * A structural modification is any operation that adds or deletes one or more mappings or,
 * in the case of access-ordered linked hash maps,
 * affects iteration order. In insertion-ordered linked hash maps, merely changing
 * the value associated with a key that is already contained
 * in the map is not a structural modification. In access-ordered linked hash maps,
 * merely querying the map with get is a structural
 * modification.
 *
 * @param <K>
 * @param <V>
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class LruMap<K, V> extends LinkedHashMap<K, V> {

  public static final float LOAD_FACTOR = 0.75F;
  protected final int limit;

  public LruMap(int initialEntries, int maxEntries) {
    super(initialEntries, LOAD_FACTOR, true);
    limit = maxEntries;
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > limit;
  }

}
