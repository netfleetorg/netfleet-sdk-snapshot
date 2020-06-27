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
package org.netfleet.sdk.network.websocket.client.impl;

import org.netfleet.sdk.network.websocket.WsRuntimeException;
import org.netfleet.sdk.network.websocket.client.WsClient;
import org.netfleet.sdk.network.websocket.connection.WsConnection;
import org.netfleet.sdk.network.websocket.connection.WsConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class SimpleWebSocketClient implements WsClient {

  private final Logger log = LoggerFactory.getLogger(SimpleWebSocketClient.class);

  private final AtomicReference<WsConnectionState> state
    = new AtomicReference<>(WsConnectionState.INITIAL);

  private WsConnection connection;
  private final Object mutex = new Object();

  public SimpleWebSocketClient(WsConnection connection) {
    this.connection = connection;
  }

  private void checkpoint(WsConnectionState cs) {
    log.info("Client status has changed from {} to {}.",
      state.getAndSet(cs),
      state.get());
  }

  @Override
  public void flush() {
  }

  @Override
  public void close() {
    synchronized (mutex) {
      connection.stop();
    }

    checkpoint(WsConnectionState.DISCONNECTED);
  }

  @Override
  public void connect() {
    if (state.get().equals(WsConnectionState.CONNECTED)) {
      throw new WsRuntimeException("Client already connected.");
    }

    synchronized (mutex) {
      if (!connection.isConnected()) {
        connection.start();
      }

      checkpoint(WsConnectionState.CONNECTED);
    }
  }

  @Override
  public WsConnectionState state() {
    return state.get();
  }

  @Override
  public void send(String message) {
    synchronized (mutex) {
      if (state.get().equals(WsConnectionState.CONNECTED)) {
        connection.dispatch(message);
      } else {
        throw new WsRuntimeException("Message cannot be sent, connect before sending message.");
      }
    }
  }

  @Override
  public Future sendAsync(String message) {
    synchronized (mutex) {
      if (state.get().equals(WsConnectionState.CONNECTED)) {
        return connection.dispatchAsync(message);
      } else {
        throw new WsRuntimeException("Message cannot be sent, connect before sending message.");
      }
    }
  }
}
