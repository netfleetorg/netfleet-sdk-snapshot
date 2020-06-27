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

import java.io.InputStream;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class FastByteArrayInputStream extends InputStream {

  /**
   * Same purpose of {@link java.io.ByteArrayInputStream#buf}.
   */
  private byte[] buffer;

  private int off;

  /**
   * Same purpose of {@link java.io.ByteArrayInputStream#count}.
   */
  private int count;

  /**
   * Same purpose of {@link java.io.ByteArrayInputStream#pos}.
   */
  private int pos;

  /**
   * Same purpose of {@link java.io.ByteArrayInputStream#mark}.
   */
  private int mark;

  /**
   * Method runs pre-conditions for valid stream.
   *
   * @param buffer the backing buffer to be used for stream, cannot be
   *               {@literal null}.
   * @param offset the first valid entry of the buffer to be used for stream,
   *               cannot be smaller than {@code 0}.
   * @param length the number of valid bytes to be used for stream, cannot be
   *               smaller than {@code 0}.
   * @throws StreamException
   */
  private void checkConstruction(final byte[] buffer, final int offset, final int length) {
    if (buffer == null) {
      throw new IllegalArgumentException("Null argument.");
    } else if (offset < 0) {
      throw new IllegalArgumentException("Offset argument must be greater than 0(zero).");
    } else if (length < 0) {
      throw new IllegalArgumentException("Length argument must be greater than 0(zero).");
    }
  }

  /**
   * @param buffer the backing buffer, cannot be {@literal null}.
   * @param off    the first valid entry of the buffer, cannot be smaller than
   *               {@code 0}.
   * @param count  the number of valid bytes, cannot be smaller than {@code 0}.
   * @throws StreamException if given parameters fails to pass pre-conditions.
   */
  FastByteArrayInputStream(final byte[] buffer, final int off, final int count) {
    checkConstruction(buffer, off, count);

    this.buffer = buffer;
    this.off = off;
    this.count = count;
  }

  /**
   * @param buffer a {@link FastByteArrayInputStream} with the given backing
   *               buffer.
   * @see #FastByteArrayInputStream(byte[], int, int)
   */
  FastByteArrayInputStream(final byte[] buffer) {
    this(buffer, 0, buffer.length);
  }

  @Override
  public boolean markSupported() {
    return true;
  }

  @Override
  public void reset() {
    pos = mark;
  }

  @Override
  public void close() {
  }

  @Override
  public void mark(final int ignore) {
    mark = pos;
  }

  @Override
  public int available() {
    return count - pos;
  }

  @Override
  public long skip(long toBeSkipped) {
    if (toBeSkipped <= count - pos) {
      pos += (int) toBeSkipped;
      return toBeSkipped;
    }

    toBeSkipped = count - pos;
    pos = count;
    return toBeSkipped;
  }

  @Override
  public int read() {
    if (count == pos) {
      return -1;
    }

    return buffer[off + pos++] & 0xFF;
  }

  @Override
  public int read(final byte[] b, final int offset, final int length) {
    if (this.count == this.pos) {
      return length == 0 ? 0 : -1;
    }

    final int n = Math.min(length, this.count - this.pos);
    System.arraycopy(buffer, this.off + this.pos, b, offset, n);

    this.pos += n;
    return n;
  }
}
