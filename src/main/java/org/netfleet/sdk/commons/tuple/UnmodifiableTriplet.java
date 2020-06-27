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

import java.util.Objects;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class UnmodifiableTriplet<K, L, M> implements Triplet<K, L, M> {

  public final K k;
  public final L l;
  public final M m;

  public UnmodifiableTriplet(K k, L l, M m) {
    this.k = k;
    this.l = l;
    this.m = m;
  }

  public static <K, L, M> UnmodifiableTriplet<K, L, M> of(K k, L l, M m) {
    return new UnmodifiableTriplet<>(k, l, m);
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
  public M getM() {
    return m;
  }

  @Override
  protected UnmodifiableTriplet<K, L, M> clone() {
    return new UnmodifiableTriplet<>(getK(), getL(), getM());
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 53 * hash + Objects.hashCode(this.k);
    hash = 53 * hash + Objects.hashCode(this.l);
    hash = 53 * hash + Objects.hashCode(this.m);
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
    final UnmodifiableTriplet<?, ?, ?> other = (UnmodifiableTriplet<?, ?, ?>) obj;
    if (!Objects.equals(this.k, other.k)) {
      return false;
    }
    if (!Objects.equals(this.l, other.l)) {
      return false;
    }
    if (!Objects.equals(this.m, other.m)) {
      return false;
    }
    return true;
  }

}
