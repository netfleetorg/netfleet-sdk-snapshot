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

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class UnmodifiablePair<K, L> implements Pair<K, L> {

  public final K k;
  public final L l;

  public UnmodifiablePair(K k, L l) {
    this.k = k;
    this.l = l;
  }

  public static <Q, W> UnmodifiablePair<Q, W> of(Q k, W l) {
    return new UnmodifiablePair<>(k, l);
  }

  @Override
  public K getK() {
    return k;
  }

  @Override
  public L getL() {
    return l;
  }

  @Override
  public UnmodifiablePair<K, L> clone() {
    return new UnmodifiablePair<>(getK(), getL());
  }
}
