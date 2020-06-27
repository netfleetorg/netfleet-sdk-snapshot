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
package org.netfleet.sdk.commons.crypto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Yeast {

  private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_".toCharArray();

  private static final int ALPHABET_LENGTH = ALPHABET.length;
  private static int seed = 0;
  private static String prev;

  private static final Map<Character, Integer> MAP = new HashMap<>(ALPHABET_LENGTH);

  static {
    for (int i = 0; i < ALPHABET_LENGTH; i++) {
      MAP.put(ALPHABET[i], i);
    }
  }

  private Yeast() {
  }

  /**
   * @param key the key.
   * @return encoded {@link String}.
   */
  public static String encode(final long key) {
    final StringBuilder encoded = new StringBuilder();
    long dividedNum = key;

    do {
      encoded.insert(0, ALPHABET[(int) (dividedNum % ALPHABET_LENGTH)]);
      dividedNum = dividedNum / ALPHABET_LENGTH;
    } while (dividedNum > 0);

    return encoded.toString();
  }

  /**
   * @param string the String to be decoded, cannot be {@literal null}.
   * @return the decoded value of given key. {@code 0l}
   * if given string is blank.
   */
  public static long decode(String string) {
    long decoded = 0l;

    for (char c : string.toCharArray()) {
      decoded = decoded * ALPHABET_LENGTH + MAP.get(c);
    }

    return decoded;
  }

  /**
   * @return string value which is yeast'd.
   */
  public static String yeast() {
    String now = encode(new Date().getTime());

    if (!now.equals(prev)) {
      seed = 0;
      prev = now;
      return now;
    }

    return now + "." + encode(seed++);
  }
}