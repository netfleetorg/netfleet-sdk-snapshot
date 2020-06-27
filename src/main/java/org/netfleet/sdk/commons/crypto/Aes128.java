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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Aes128 {

  private Aes128() {
  }

  /**
   * @param secret
   * @param key
   * @return
   * @throws InvalidKeyException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchPaddingException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   */
  public static byte[] encrypt(final String secret, final String key) throws InvalidKeyException,
    NoSuchAlgorithmException,
    NoSuchPaddingException,
    IllegalBlockSizeException,
    BadPaddingException {

    final Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
    final Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, aesKey);

    return cipher.doFinal(secret.getBytes());
  }

  /**
   * @param encrypted
   * @param key
   * @return
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   * @throws InvalidKeyException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchPaddingException
   */
  public static String decrypt(final byte[] encrypted, final String key) throws IllegalBlockSizeException,
    BadPaddingException,
    InvalidKeyException,
    NoSuchAlgorithmException,
    NoSuchPaddingException {

    final Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
    final Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, aesKey);

    return String.valueOf(cipher.doFinal(encrypted));
  }

}