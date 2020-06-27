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
import org.netfleet.sdk.network.websocket.client.StompClient;
import org.netfleet.sdk.network.websocket.connection.WsConnection;
import org.netfleet.sdk.network.websocket.connection.WsConnectionState;
import org.netfleet.sdk.network.websocket.stomp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class SimpleStompClient implements StompClient {

  private final Logger log = LoggerFactory.getLogger(SimpleStompClient.class);

  private final AtomicReference<WsConnectionState> state
    = new AtomicReference<>(WsConnectionState.INITIAL);

  private final CopyOnWriteArrayList<StompTopic> topics
    = new CopyOnWriteArrayList<>();

  private WsConnection connection;

  private final Object mutex = new Object();

  public SimpleStompClient(WsConnection connection) {
    this.connection = connection;
  }

  private void checkpoint(WsConnectionState cs) {
    log.info("Client status has changed from {} to {}.",
      state.getAndSet(cs),
      state.get());
  }

  private void checkConnected() {
    if (!state.get().equals(WsConnectionState.CONNECTED)) {
      throw new WsRuntimeException("Client is not connected.");
    }
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
    throw new UnsupportedOperationException("Use SimpleStompClient::publish.");
  }

  @Override
  public Future sendAsync(String message) {
    throw new UnsupportedOperationException("Use SimpleStompClient::publishAsync.");
  }

  @Override
  public void publish(StompFrame stompFrame) {
    checkConnected();

    synchronized (mutex) {
      connection.dispatch(stompFrame);
    }
  }

  @Override
  public Future publishAsync(StompFrame stompFrame) {
    checkConnected();

    synchronized (mutex) {
      return connection.dispatchAsync(stompFrame);
    }
  }

  @Override
  public StompTopic subscribe(StompSubscription subscription) {
    checkConnected();

    synchronized (mutex) {
      String destination = subscription.getDestination().getNamespace();

      StompFrame frame = new StompFrame(StompCommand.SUBSCRIBE);
      frame.getHeader().addHeader(StompHeaders.ID, subscription.getId());
      frame.getHeader().addHeader(StompHeaders.DESTINATION, destination);
      connection.dispatch(frame);

      StompTopic topic = new StompTopic(destination);
      topics.add(topic);

      return getTopic(destination);
    }
  }

  @Override
  public StompTopic unsubscribe(StompSubscription subscription) {
    checkConnected();

    String destination = subscription.getDestination().getNamespace();
    StompTopic topic = getTopic(destination);
    if (topic == null) {
      return null;
    }

    synchronized (mutex) {
      StompFrame frame = new StompFrame(StompCommand.UNSUBSCRIBE);
      frame.getHeader().addHeader(StompHeaders.ID, destination);

      connection.dispatch(frame);
      int index = -1;
      for (int i = 0; i < topics.size(); i++) {
        if (topics.get(i).getNamespace().equalsIgnoreCase(destination)) {
          index = i;
          break;
        }
      }

      if (index == -1) {
        throw new WsRuntimeException("This should never happen.");
      }

      return topics.remove(index);
    }
  }

  @Override
  public StompTopic getTopic(String destination) {
    for (StompTopic topic: topics) {
      if (topic.getNamespace().equalsIgnoreCase(destination)) {
        return topic;
      }
    }

    return null;
  }
}
