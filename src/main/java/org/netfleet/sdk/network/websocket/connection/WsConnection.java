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

import org.netfleet.sdk.network.websocket.WsRuntimeException;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public interface WsConnection extends Closeable {

  @Override
  void close() throws IOException;

  /**
   * @throws WsRuntimeException if the connection cannot start due to some internal
   *                            error.
   */
  void start() throws WsRuntimeException;

  /**
   * @throws WsRuntimeException if the connection cannot stop due to some internal
   *                            error.
   */
  void stop() throws WsRuntimeException;

  /**
   * Dispatch the given message.
   *
   * @param message the message object to be dispatched.
   */
  void dispatch(Object message);

  /**
   * Dispatch the given message async.
   *
   * @param message the message to be dispatched.
   * @return a {@link Future} object of the dispatch.
   */
  Future<Object> dispatchAsync(Object message);

  /**
   * Check whether connection is established or not.
   *
   * @return {@code true} if connection established.
   * {@code false} otherwise.
   */
  boolean isConnected();

  /**
   * @return {@link WsConnectionMetadata} of the current connection.
   */
  WsConnectionMetadata getMetadata();

  String getId();

}
