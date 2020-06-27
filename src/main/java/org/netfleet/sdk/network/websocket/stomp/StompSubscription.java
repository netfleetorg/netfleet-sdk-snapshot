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
package org.netfleet.sdk.network.websocket.stomp;

import org.netfleet.sdk.network.websocket.WsDestination;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class StompSubscription implements Serializable {

  private String id;
  private WsDestination destination;
  private String ackMode;

  public StompSubscription() {
  }

  public StompSubscription(String id, WsDestination destination) {
    this.id = id;
    this.destination = destination;
  }

  public StompSubscription(String id, WsDestination destination, String ackMode) {
    this.id = id;
    this.destination = destination;
    this.ackMode = ackMode;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public WsDestination getDestination() {
    return destination;
  }

  public void setDestination(WsDestination destination) {
    this.destination = destination;
  }

  public String getAckMode() {
    return ackMode;
  }

  public void setAckMode(String ackMode) {
    this.ackMode = ackMode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StompSubscription that = (StompSubscription) o;
    return Objects.equals(id, that.id) &&
      Objects.equals(destination, that.destination) &&
      Objects.equals(ackMode, that.ackMode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, destination, ackMode);
  }
}
