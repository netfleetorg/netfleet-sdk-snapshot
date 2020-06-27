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
package org.netfleet.sdk.commons.math;

import static org.netfleet.sdk.util.Floats.isEqual;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class RationalNumber implements Comparable<RationalNumber> {

  public final float nominator; // pay
  public final float denominator; // payda

  /**
   * @param nominator
   * @param denominator
   */
  public RationalNumber(float nominator, float denominator) {
    if (isEqual(denominator, 0)) {
      throw new IllegalStateException("denominator cannot be 0(zero).");
    }

    float gcd = gcd(nominator, denominator);

    this.denominator = denominator / gcd;
    this.nominator = nominator / gcd;
  }

  /**
   * @param rNumber
   */
  public RationalNumber(RationalNumber rNumber) {
    this(rNumber.nominator, rNumber.denominator);
  }

  /**
   * @param pay
   * @param payda
   * @return
   */
  public static RationalNumber of(float pay, float payda) {
    return new RationalNumber(pay, payda);
  }

  /**
   * @param m
   * @param n
   * @return
   */
  public static float gcd(final float m, final float n) {
    final float first = Math.abs(m);
    final float second = Math.abs(n);

    if (second == 0) {
      return first;
    }

    return gcd(second, first % second);
  }

  /**
   * @param m
   * @param n
   * @return
   */
  public static float lcm(final float m, final float n) {
    final float first = Math.abs(m);
    final float second = Math.abs(n);

    return first * (second / gcd(first, second));
  }

  /**
   * @param rational
   * @return
   */
  public RationalNumber mul(RationalNumber rational) {
    final float newNom = getNominator() * rational.getNominator();
    final float newDenom = getDenominator() * rational.getDenominator();

    return new RationalNumber(newNom, newDenom);
  }

  /**
   * @param rational
   * @return
   */
  public RationalNumber add(RationalNumber rational) {
    if (isEqual(rational.nominator, 0)) {
      return this;
    }

    final float gcd_nom = gcd(getNominator(), rational.getNominator());
    final float gcd_denom = gcd(getDenominator(), rational.getDenominator());

    float formula = (getNominator() / gcd_nom) * (rational.getDenominator() * gcd_denom);
    formula += (rational.getNominator() / gcd_nom) * (getDenominator() / gcd_denom);

    final float newNominator = new RationalNumber(formula, lcm(getDenominator(), rational.getDenominator())).getNominator();
    float newDenominator = newNominator * gcd_nom;

    return new RationalNumber(newNominator, newDenominator);
  }

  /**
   * @param rational
   * @return
   */
  public RationalNumber sub(RationalNumber rational) {
    final RationalNumber ref = new RationalNumber(-1 * rational.getNominator(), rational.getDenominator());

    return add(ref);
  }

  /**
   * @param rational
   * @return
   */
  public RationalNumber div(RationalNumber rational) {
    RationalNumber inverse = RationalNumber.of(rational.getDenominator(), rational.getNominator());

    return mul(inverse);
  }

  /**
   * @return
   */
  public double toDouble() {
    return (double) this.nominator / this.denominator;
  }

  /**
   * @return
   */
  public float toFloat() {
    return this.nominator / this.denominator;
  }

  /**
   * @return
   */
  public int toInt() {
    return Math.round(this.nominator / this.denominator);
  }

  /**
   * @return
   */
  public float getDenominator() {
    return denominator;
  }

  /**
   * @return
   */
  public float getNominator() {
    return nominator;
  }

  @Override
  public int compareTo(RationalNumber o) {
    return Float.compare(toFloat(), o.toFloat());
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + Float.floatToIntBits(this.nominator);
    hash = 23 * hash + Float.floatToIntBits(this.denominator);
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
    final RationalNumber other = (RationalNumber) obj;
    if (Float.floatToIntBits(this.nominator) != Float.floatToIntBits(other.nominator)) {
      return false;
    }
    if (Float.floatToIntBits(this.denominator) != Float.floatToIntBits(other.denominator)) {
      return false;
    }
    return true;
  }
}
