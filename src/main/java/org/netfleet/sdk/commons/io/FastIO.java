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

import org.netfleet.sdk.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class FastIO {

  private FastIO() {
  }

  /**
   * Default limit of byte size.
   */
  private static final int DEFAULT_BUFFER_SIZE = 1024 * 5;

  /**
   * @param in
   * @param out
   * @throws IOException
   * @see #fastCopy(java.nio.channels.ReadableByteChannel,
   * java.nio.channels.WritableByteChannel)
   * <p>
   * Copies {@link InputStream} to {@link OutputStream} with channels. Method
   * is significantly fast and thread-safe.
   */
  public static void fastCopy(final InputStream in, final OutputStream out) throws IOException {
    final ReadableByteChannel inputChannel = Channels.newChannel(in);
    final WritableByteChannel outputChannel = Channels.newChannel(out);

    fastCopy(inputChannel, outputChannel);
  }

  /**
   * Copies {@link ReadableByteChannel} to {@link WritableByteChannel} with
   * channels. Method is significantly fast and thread-safe.
   *
   * @param rbc
   * @param wbc
   * @throws IOException
   */
  public static void fastCopy(final ReadableByteChannel rbc,
                              final WritableByteChannel wbc)
    throws IOException {
    Assert.requireNonNull(rbc);
    Assert.requireNonNull(wbc);

    ByteBuffer buffer = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
    try {
      while (rbc.read(buffer) != -1) {
        buffer.flip();

        wbc.write(buffer);
        buffer.compact();
      }

      buffer.flip();
      while (buffer.hasRemaining()) {
        wbc.write(buffer);
      }
    } finally {
      rbc.close();
      wbc.close();
    }
  }

  /**
   * @param buffer {@link java.io.ByteArrayInputStream#buf}.
   * @param off    the offset of stream.
   * @param count  {@link java.io.ByteArrayInputStream#count}
   * @return a {@link FastByteArrayInputStream} instance with given
   * parameters.
   */
  public static InputStream newFastByteArrayInputStream(final byte[] buffer,
                                                        final int off,
                                                        final int count) {
    return new FastByteArrayInputStream(buffer, off, count);
  }

  /**
   * @param buffer {@link java.io.ByteArrayInputStream#buf}.
   * @return a {@link FastByteArrayInputStream} instance with given buffer.
   */
  public static InputStream newFastByteArrayInputStream(final byte[] buffer) {
    return new FastByteArrayInputStream(buffer);
  }

}
