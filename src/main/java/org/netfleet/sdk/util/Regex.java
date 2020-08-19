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

import java.util.regex.Pattern;

public final class Regex {
  private Regex() {
  }

  public static final Pattern SPECIAL_CHARACTER_REGEX
      = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

  public static final Pattern VALID_NAME_REGEX
      = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");

  public static final Pattern VALID_SURNAME_REGEX
      = Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,44}$");

  public static final Pattern VALID_NUMERIC_REGEX
      = Pattern.compile("-?\\d+(\\.\\d+)?");

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

}
