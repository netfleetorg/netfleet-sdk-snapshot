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

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class RSA {

  private final MutexFactory mutexFactory;

  private final BigInteger modulus;
  private final BigInteger privateKey;
  private final BigInteger publicKey;
  private final Charset charset;

  private final int bits;

  public RSA(int bitslen, Charset charset) {
    this.mutexFactory = new MutexFactory();
    this.charset = charset;
    this.bits = bitslen;

    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bits / 2, 100, r);
    BigInteger q = new BigInteger(bits / 2, 100, r);
    modulus = p.multiply(q);

    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    BigInteger tmp = new BigInteger("3");
    while (m.gcd(tmp).intValue() > 1) {
      tmp = tmp.add(new BigInteger("2"));
    }

    privateKey = tmp.modInverse(m);
    publicKey = tmp;
  }

  public RSA(int bitslen) {
    this(bitslen, Charset.forName("UTF-8"));
  }

  public byte[] encrypt(final String wb) {
    byte[] encrypted;

    synchronized (mutexFactory.get(this)) {
      BigInteger bigInt = new BigInteger(wb.getBytes());
      bigInt = bigInt.modPow(publicKey, modulus);

      String tmp = bigInt.toString();
      encrypted = tmp.getBytes(charset);
    }

    return encrypted;
  }

  public String decrypt(final byte[] wb) {
    String decrypted;

    synchronized (mutexFactory.get(this)) {
      BigInteger bigInt = new BigInteger(new String(wb, charset));
      bigInt = bigInt.modPow(privateKey, modulus);

      decrypted = new String(bigInt.toByteArray(), charset);
    }

    return decrypted;
  }

}