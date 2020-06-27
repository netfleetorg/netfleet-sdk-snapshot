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
package org.netfleet.sdk.integration.netgsm.context.impl;

import org.netfleet.sdk.integration.netgsm.Authentication;

import java.util.List;

public abstract class AbstractDataContext implements Authentication {

  private String username;
  private String password;
  private String baseUrl;
  private List<String> numbers;

  protected AbstractDataContext(String username, String password, String baseUrl) {
    this(username, password, baseUrl, null);
  }

  public AbstractDataContext(String username, String password, String baseUrl, List<String> numbers) {
    this.username = username;
    this.password = password;
    this.baseUrl = baseUrl;
    this.numbers = numbers;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  @Override
  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public void setNumbers(List<String> numbers) {
    this.numbers = numbers;
  }

  public List<String> getNumbers() {
    return numbers;
  }
}
