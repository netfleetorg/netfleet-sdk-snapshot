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
package org.netfleet.sdk.commons.java8;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Lazy<T> {

  private transient volatile Optional<? extends T> instance = Optional.empty();
  private final Supplier<T> supplier;

  private Lazy(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  public static <T> Lazy<T> of(final Supplier<T> supplier) {
    return new Lazy<>(supplier);
  }

  public boolean isPresent() {
    return (instance != null) && instance.isPresent();
  }

  public T get() {
    if (!isPresent()) {
      instance = Optional.of(supplier.call());
    }

    return instance.get();
  }

  public Optional<T> getIf(final Predicate<T> predicate) {
    if (predicate != null) {
      final T lazy = get();
      if (lazy != null && predicate.call(lazy)) {
        return Optional.ofNullable(lazy);
      }
    }

    return Optional.empty();
  }

}