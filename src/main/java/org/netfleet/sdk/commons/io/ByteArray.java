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
package org.netfleet.sdk.commons.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class ByteArray {

  private final byte[] bytes;

  /**
   * Can only be called from {@link #empty()}.
   */
  private ByteArray() {
    this.bytes = null;
  }

  /**
   * @param bytesParam the byte array, cannot be {@literal null}.
   * @throws IllegalArgumentException if given argument is {@literal null}.
   */
  public ByteArray(final byte[] bytesParam) {
    if (bytesParam == null) {
      throw new IllegalArgumentException("Null argument.");
    }

    this.bytes = bytesParam;
  }

  /**
   * @return an empty {@link ByteArray}.
   */
  public static ByteArray empty() {
    return new ByteArray();
  }

  /**
   * @param bytes the byte array, cannot be {@literal null}.
   * @return new {@link ByteArray} created from given argument.
   * @throws IllegalArgumentException if given argument is {@literal null}.
   */
  public static ByteArray of(final byte[] bytes) {
    return new ByteArray(bytes);
  }

  /**
   * @param bytes the byte array, can be {@literal null}.
   * @return new {@link ByteArray} created from given argument.
   */
  public static ByteArray ofNullable(final byte[] bytes) {
    if (bytes == null) {
      return empty();
    } else {
      return new ByteArray(bytes);
    }
  }

  /**
   * @param string the string value to be wrapped, cannot be {@literal null}.
   * @return new {@link ByteArray} created from given argument.
   * @throws IllegalArgumentException if given argument is {@literal null}.
   */
  public static ByteArray of(final String string) {
    return new ByteArray(string.getBytes());
  }

  /**
   * @param string the byte array, can be {@literal null}.
   * @return new {@link ByteArray} created from given argument.
   */
  public static ByteArray ofNullable(final String string) {
    if (string == null) {
      return empty();
    } else {
      return new ByteArray(string.getBytes());
    }
  }

  /**
   * @param string  the string value to be wrapped, cannot be {@literal null}.
   * @param charset the charset to be use for wrapping, cannot be
   *                {@literal null}.
   * @return new {@link ByteArray} created from given argument.
   * @throws IllegalArgumentException if given {@link String} or
   *                                  {@link Charset} is {@literal null}.
   */
  public static ByteArray of(final String string, final Charset charset) {
    if (string == null) {
      throw new IllegalArgumentException("Null argument.");
    }

    return new ByteArray(string.getBytes(charset));
  }

  /**
   * {@literal 'UTF-8'} will be used if given {@link Charset} is
   * {@literal null}.
   *
   * @param string  the string value to be wrapped, can be {@literal null}.
   * @param charset the charset to be use for wrapping, can be
   *                {@literal null}.
   * @return new {@link ByteArray} created from given argument.
   */
  public static ByteArray ofNullable(final String string, final Charset charset) {
    if (string == null) {
      return empty();
    } else if (charset == null) {
      return new ByteArray(string.getBytes(StandardCharsets.UTF_8));
    } else {
      return new ByteArray(string.getBytes(charset));
    }
  }

  public static ByteArray of(final Serializable serializableObject) {
    if (serializableObject == null) {
      return ByteArray.empty();
    }

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;

    try {
      out = new ObjectOutputStream(bos);
      out.writeObject(serializableObject);
      out.flush();

      byte[] bytes = bos.toByteArray();
      return ByteArray.of(bytes);
    } catch (IOException exception) {
      return ByteArray.empty();
    } finally {
      try {
        bos.close();
        if (out != null) {
          out.close();
        }
      } catch (IOException ignore) {
      }
    }
  }

  /**
   * @return {@code true} if {@link #bytes} is {@literal null}. {@code false}
   * otherwise.
   */
  public boolean isEmpty() {
    return getBytes() == null;
  }

  /**
   * @return wrapped byte array, may be {@literal null}.
   */
  public byte[] getBytes() {
    return ByteArray.this.bytes;
  }

  public String toString(final Charset charset) {
    return new String(bytes, charset);
  }

  @Override
  public String toString() {
    return new String(bytes, StandardCharsets.UTF_8);
  }

}
