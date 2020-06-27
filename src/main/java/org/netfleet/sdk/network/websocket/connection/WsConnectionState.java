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
package org.netfleet.sdk.network.websocket.connection;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public enum WsConnectionState {

  /**
   * Indicates that {@link WsConnection} has never connected or disconnected
   * before.
   */
  INITIAL,

  /**
   * Indicates that {@link WsConnection} is trying to connect to server.
   */
  CONNECTING,

  /**
   * Indicates that {@link WsConnection} connected to server. In this state
   * {@link WsConnection} can be used to do what it provides.
   */
  CONNECTED,

  /**
   * Indicates that {@link WsConnection} is stopped. More clearly, connection is
   * established to server but all operations going to be ignored during this
   * state.
   */
  STOPPED,

  /**
   * Indicates that {@link WsConnection} is trying to disconnect from server.
   */
  DISCONNECTING,

  /**
   * Indicates that {@link WsConnection} disconnected from server.
   */
  DISCONNECTED;

  @Override
  public String toString() {
    return name();
  }

}
