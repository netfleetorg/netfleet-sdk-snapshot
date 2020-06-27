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
package org.netfleet.sdk.util;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class Strings {

  public static final String DOT = ".";
  public static final String COMMA = ",";
  public static final String OPEN_PARENTHESIS = "(";
  public static final String CLOSE_PARENTHESIS = ")";
  public static final String COLON = ":";
  public static final String SEMICOLON = ";";
  public static final String SPACE = " ";
  public static final String OPEN_BRACKET = "[";
  public static final String CLOSE_BRACKET = "]";
  public static final String EMPTY = "";
  public static final String LF = "\n";
  public static final String CR = "\r";
  public static final String NULL = "null";

  private Strings() {
  }

  public static boolean isEmpty(String string) {
    return (string == null) || string.isEmpty();
  }

  public static boolean isNotEmpty(String string) {
    return !isEmpty(string);
  }

  public static String trim(final String str) {
    return str == null ? null : str.trim();
  }

  public static String blankIfEmpty(String string) {
    if (isEmpty(string))
      return "";
    else
      return string;
  }

  /**
   * @param string
   * @return
   */
  public static String inQuote(final String string) {
    return new StringBuilder()
      .append("'")
      .append(string)
      .append("'")
      .toString();
  }

  /**
   * @param arg
   * @return
   */
  public static String join(final String... arg) {
    if (arg == null) {
      return "";
    }
    StringBuilder builder = new StringBuilder();

    for (String each : arg) {
      builder.append((each != null) ? each : "");
    }

    return builder.toString();
  }

  /**
   * @param seperator
   * @param arg
   * @return
   */
  public static String join(final Character seperator, final String... arg) {
    if (arg == null) {
      return "";
    }
    Character tmp = (seperator != null) ? seperator : Character.MIN_VALUE;
    StringBuilder builder = new StringBuilder();

    for (String each : arg) {
      builder.append((each != null) ? each : "");
      builder.append(tmp);
    }

    return builder.toString();
  }

  /**
   * @param string
   * @param from
   * @return
   */
  public static String substring(final String string, final int from) {
    String newString = string;
    int length = newString.length() + from;

    return newString.substring(0, length);
  }

  /**
   * @param string
   * @return
   */
  public static String inBrackets(final String string) {
    return "[" + string + "]";
  }

  /**
   * @param string
   * @return
   */
  public static String inCurlyBraces(final String string) {
    return "{" + string + "}";
  }

  /**
   * @param string
   * @return true if given String is equal to "". Otherwise false.
   */
  public static boolean isBlank(final String string) {
    return string.equals("");
  }

  /**
   * @param string
   * @return true if given String is either null or equals to "". Otherwise
   * false.
   */
  public static boolean nullOrBlank(final String string) {
    return Objects.isNull(string) || isBlank(string);
  }

  /**
   * @param integer
   * @return
   */
  public static String toString(final int integer) {
    return String.valueOf(integer);
  }

  /**
   * @param dbl
   * @return
   */
  public static String toString(final double dbl) {
    return String.valueOf(dbl);
  }

  /**
   * @param bool
   * @return
   */
  public static String toString(final boolean bool) {
    return bool ? "true" : "false";
  }

  /**
   * @param string
   * @return
   */
  public static double toDouble(final String string) {
    return Double.valueOf(string);
  }

  /**
   * @param string
   * @return
   */
  public static double toInt(final String string) {
    return Integer.valueOf(string);
  }

  /**
   * @param string
   * @return
   */
  public static boolean toBool(final String string) {
    return Boolean.valueOf(string);
  }

  /**
   * @param string
   * @return Same string without first and last letter of given String.
   */
  public static String clear(final String string) {
    return string.substring(1, string.length() - 1);
  }

  /**
   * @param object
   * @param orElse
   * @return
   */
  public static String getIfNull(final String object, String orElse) {
    return (object == null) ? orElse : object;
  }
}
