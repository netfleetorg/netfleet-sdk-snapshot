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

import org.netfleet.sdk.util.Assert;

import java.util.NoSuchElementException;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Optional<T> {

  private final T instance;

  /**
   * Identical to {@link #empty()}.
   */
  private Optional() {
    this.instance = null;
  }

  /**
   * @param instance the object instance, cannot be {@literal null}.
   */
  private Optional(T instance) {
    Assert.requireNonNull(instance);

    this.instance = instance;
  }

  /**
   * @param <T>   the generic sign of the object.
   * @param value the object, cannot be {@literal null}.
   * @return a new {@link Optional} with value of given object.
   */
  public static <T> Optional<T> of(final T value) {
    Assert.requireNonNull(value);

    return new Optional<>(value);
  }

  /**
   * @param <T>   the generic sign of the object.
   * @param value the object, can be {@literal null}.
   * @return a new {@link Optional} with value of given object.
   */
  public static <T> Optional<T> ofNullable(final T value) {
    return new Optional<>(value);
  }

  /**
   * @param <T> the generic sign of the object
   * @return empty {@link Optional}.
   */
  public static <T> Optional<T> empty() {
    return (Optional<T>) new Optional<>();
  }

  /**
   * @return {@code true} if {@link #instance} is not {@literal null}.
   * {@code false} otherwise.
   */
  public boolean isPresent() {
    return instance != null;
  }

  /**
   * @param consumer the {@link Consumer}, can be {@literal null}.
   */
  public void ifPresent(final Consumer<T> consumer) {
    if (isPresent()) {
      if (consumer != null) {
        consumer.call(instance);
      }
    }
  }

  /**
   * @param alternate the alternative of object. can be {@literal null}.
   * @return current instance if this {@link Optional} is present. Given
   * object instance otherwise.
   */
  public T getIfNull(final T alternate) {
    if (isPresent()) {
      return get();
    } else {
      return alternate;
    }
  }

  /**
   * @return the instance of this {@link Optional}. Can be {@literal null}.
   * @throws NoSuchElementException if {@link #instance} is {@literal null}.
   * @see #isPresent()
   */
  public T get() {
    if (instance == null) {
      throw new NoSuchElementException("No value present");
    }

    return this.instance;
  }

  /**
   * @param predicate the {@link Predicate}, cannot be {@literal null}.
   * @return {@code this} if {@link Predicate#call(java.lang.Object)} is
   * {@code true}. Otherwise {@link #empty()}.
   * @throws IllegalArgumentException if the {@link Predicate} is
   *                                  {@literal null}.
   * @see Predicate
   * @see #isPresent()
   */
  public Optional<T> filter(final Predicate<T> predicate) {
    Assert.requireNonNull(predicate);

    if (isPresent()) {
      boolean selected = predicate.call(instance);
      Optional<T> empty = Optional.empty();
      if (selected) {
        return this;
      } else {
        return empty;
      }
    } else {
      return this;
    }
  }
}
