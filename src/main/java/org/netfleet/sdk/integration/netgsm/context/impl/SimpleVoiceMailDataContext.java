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
import org.netfleet.sdk.integration.netgsm.SimpleAuthentication;
import org.netfleet.sdk.integration.netgsm.context.VoiceMailDataContext;

import java.util.Date;
import java.util.List;

public class SimpleVoiceMailDataContext extends AbstractDataContext implements VoiceMailDataContext {

  private String audioId;
  private Date startDate;
  private Date stopDate;
  boolean keyEnabled = false;

  public SimpleVoiceMailDataContext(String username, String password, String baseUrl) {
    super(username, password, baseUrl);
  }

  public SimpleVoiceMailDataContext(String username, String password, String baseUrl, List<String> numbers) {
    super(username, password, baseUrl, numbers);
  }

  @Override
  public String getAudioId() {
    return audioId;
  }

  public void setAudioId(String audioId) {
    this.audioId = audioId;
  }

  @Override
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @Override
  public Date getStopDate() {
    return stopDate;
  }

  public void setStopDate(Date stopDate) {
    this.stopDate = stopDate;
  }

  public boolean isKeyEnabled() {
    return keyEnabled;
  }

  public void setKeyEnabled(boolean keyEnabled) {
    this.keyEnabled = keyEnabled;
  }

  @Override
  public Authentication getAuthentication() {
    return new SimpleAuthentication(getUsername(), getPassword(), getBaseUrl());
  }
}
