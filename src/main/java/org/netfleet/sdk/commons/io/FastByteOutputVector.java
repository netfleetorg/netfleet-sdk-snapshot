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

import org.netfleet.sdk.annotation.Mutable;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@Mutable
public class FastByteOutputVector {

  private byte[] data;

  private int length;

  public FastByteOutputVector() {
    data = new byte[64];
  }

  public FastByteOutputVector(final int initialSize) {
    data = new byte[initialSize];
  }

  public FastByteOutputVector putByte(final int b) {
    int index = this.length;
    if (index + 1 > data.length) {
      enlarge(1);
    }
    data[index++] = (byte) b;
    this.length = index;
    return this;
  }

  FastByteOutputVector put11(final int b1, final int b2) {
    int index = this.length;
    if (index + 2 > data.length) {
      enlarge(2);
    }
    byte[] bytes = this.data;
    bytes[index++] = (byte) b1;
    bytes[index++] = (byte) b2;
    this.length = index;
    return this;
  }

  public FastByteOutputVector putShort(final int s) {
    int index = this.length;
    if (index + 2 > data.length) {
      enlarge(2);
    }
    byte[] data = this.data;
    data[index++] = (byte) (s >>> 8);
    data[index++] = (byte) s;
    this.length = index;
    return this;
  }

  FastByteOutputVector put12(final int b, final int s) {
    int length = this.length;
    if (length + 3 > data.length) {
      enlarge(3);
    }
    byte[] data = this.data;
    data[length++] = (byte) b;
    data[length++] = (byte) (s >>> 8);
    data[length++] = (byte) s;
    this.length = length;
    return this;
  }

  public FastByteOutputVector putInt(final int i) {
    int length = this.length;
    if (length + 4 > data.length) {
      enlarge(4);
    }
    byte[] data = this.data;
    data[length++] = (byte) (i >>> 24);
    data[length++] = (byte) (i >>> 16);
    data[length++] = (byte) (i >>> 8);
    data[length++] = (byte) i;
    this.length = length;
    return this;
  }

  public FastByteOutputVector putLong(final long l) {
    int length = this.length;
    if (length + 8 > data.length) {
      enlarge(8);
    }
    byte[] data = this.data;
    int i = (int) (l >>> 32);
    data[length++] = (byte) (i >>> 24);
    data[length++] = (byte) (i >>> 16);
    data[length++] = (byte) (i >>> 8);
    data[length++] = (byte) i;
    i = (int) l;
    data[length++] = (byte) (i >>> 24);
    data[length++] = (byte) (i >>> 16);
    data[length++] = (byte) (i >>> 8);
    data[length++] = (byte) i;
    this.length = length;
    return this;
  }

  public FastByteOutputVector putUTF8(final String s) {
    int charLength = s.length();
    if (charLength > 65535) {
      throw new IllegalArgumentException();
    }
    int len = length;
    if (len + 2 + charLength > data.length) {
      enlarge(2 + charLength);
    }
    byte[] data = this.data;
    // optimistic algorithm: instead of computing the byte length and then
    // serializing the string (which requires two loops), we assume the byte
    // length is equal to char length (which is the most frequent case), and
    // we start serializing the string right away. During the serialization,
    // if we find that this assumption is wrong, we continue with the
    // general method.
    data[len++] = (byte) (charLength >>> 8);
    data[len++] = (byte) charLength;
    for (int i = 0; i < charLength; ++i) {
      char c = s.charAt(i);
      if (c >= '\001' && c <= '\177') {
        data[len++] = (byte) c;
      } else {
        int byteLength = i;
        for (int j = i; j < charLength; ++j) {
          c = s.charAt(j);
          if (c >= '\001' && c <= '\177') {
            byteLength++;
          } else if (c > '\u07FF') {
            byteLength += 3;
          } else {
            byteLength += 2;
          }
        }
        if (byteLength > 65535) {
          throw new IllegalArgumentException();
        }
        data[length] = (byte) (byteLength >>> 8);
        data[length + 1] = (byte) byteLength;
        if (length + 2 + byteLength > data.length) {
          length = len;
          enlarge(2 + byteLength);
          data = this.data;
        }
        for (int j = i; j < charLength; ++j) {
          c = s.charAt(j);
          if (c >= '\001' && c <= '\177') {
            data[len++] = (byte) c;
          } else if (c > '\u07FF') {
            data[len++] = (byte) (0xE0 | c >> 12 & 0xF);
            data[len++] = (byte) (0x80 | c >> 6 & 0x3F);
            data[len++] = (byte) (0x80 | c & 0x3F);
          } else {
            data[len++] = (byte) (0xC0 | c >> 6 & 0x1F);
            data[len++] = (byte) (0x80 | c & 0x3F);
          }
        }
        break;
      }
    }
    length = len;
    return this;
  }

  public FastByteOutputVector putByteArray(final byte[] b, final int off, final int len) {
    if (length + len > data.length) {
      enlarge(len);
    }
    if (b != null) {
      System.arraycopy(b, off, data, length, len);
    }
    length += len;
    return this;
  }

  private void enlarge(final int size) {
    int length1 = 2 * data.length;
    int length2 = length + size;
    byte[] newData = new byte[length1 > length2 ? length1 : length2];
    System.arraycopy(data, 0, newData, 0, length);
    data = newData;
  }

  public byte[] buffer() {
    return data;
  }

  public int length() {
    return length;
  }
}
