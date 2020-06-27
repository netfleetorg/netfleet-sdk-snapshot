/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (C) 2019 Terra Yazılım Ltd Şti - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License 3 only, as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details (a copy is included in the
 * LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 * Please contact Terra Yazılım, Konya Teknoloji Geliştirme Bölgesi
 * Büyük Kayacık Mah. 101. Cad. No:2 42250 - Selçuklu Konya or visit
 * www.terrayazilim.com.tr if you need additional information or have
 * any questions.
 */
package org.netfleet.sdk.integration.netgsm.context.impl;

import org.netfleet.sdk.integration.netgsm.Authentication;
import org.netfleet.sdk.integration.netgsm.SimpleAuthentication;
import org.netfleet.sdk.integration.netgsm.context.SmsDataContext;

import java.util.List;

public class SimpleSmsDataContext extends AbstractDataContext implements SmsDataContext {

  private String header;
  private String message;

  public SimpleSmsDataContext(String username, String password, String baseUrl) {
    super(username, password, baseUrl);
  }

  public SimpleSmsDataContext(String username, String password, String baseUrl, List<String> numbers) {
    super(username, password, baseUrl, numbers);
  }

  public void setHeader(String header) {
    this.header = header;
  }

  @Override
  public String getHeader() {
    return header;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public Authentication getAuthentication() {
    return new SimpleAuthentication(getUsername(), getPassword(), getBaseUrl());
  }
}
