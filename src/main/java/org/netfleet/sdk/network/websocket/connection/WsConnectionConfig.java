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

import org.netfleet.sdk.network.websocket.event.*;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class WsConnectionConfig {

  private boolean stompProtocolEnabled;

  private WsConnectionMetadata metadata;
  private WsConnectionCredential credential;

  private WsTextMessageListener textMessageListener;
  private WsTextMessageSentListener textMessageSentListener;

  private WsBytesMessageListener bytesMessageListener;
  private WsBytesMessageSentListener bytesMessageSentListener;

  private WsConnectedListener connectedListener;
  private WsDisconnectedListener disconnectedListener;
  private WsFailureListener failureListener;

  public WsConnectionConfig() {
  }

  public WsConnectionConfig(boolean stompProtocolEnabled) {
    this.stompProtocolEnabled = stompProtocolEnabled;
  }

  public WsConnectionConfig(Builder b) {
    this(b.stompProtocolEnabled,
      b.metadata,
      b.credential,
      b.textMessageListener,
      b.textMessageSentListener,
      b.bytesMessageListener,
      b.bytesMessageSentListener,
      b.connectedListener,
      b.disconnectedListener,
      b.failureListener);
  }

  public WsConnectionConfig(boolean stompProtocolEnabled,
                            WsConnectionMetadata metadata,
                            WsConnectionCredential credential,
                            WsTextMessageListener textMessageListener,
                            WsTextMessageSentListener textMessageSentListener,
                            WsBytesMessageListener bytesMessageListener,
                            WsBytesMessageSentListener bytesMessageSentListener,
                            WsConnectedListener connectedListener,
                            WsDisconnectedListener disconnectedListener,
                            WsFailureListener failureListener) {
    this.stompProtocolEnabled = stompProtocolEnabled;
    this.metadata = metadata;
    this.credential = credential;
    this.textMessageListener = textMessageListener;
    this.textMessageSentListener = textMessageSentListener;
    this.bytesMessageListener = bytesMessageListener;
    this.bytesMessageSentListener = bytesMessageSentListener;
    this.connectedListener = connectedListener;
    this.disconnectedListener = disconnectedListener;
    this.failureListener = failureListener;
  }

  public WsConnectionConfig enableStompProtocol() {
    setStompProtocolEnabled(true);
    return this;
  }

  public WsConnectionConfig disableStompProtocol() {
    setStompProtocolEnabled(false);
    return this;
  }

  public void setStompProtocolEnabled(boolean stompProtocolEnabled) {
    this.stompProtocolEnabled = stompProtocolEnabled;
  }

  public WsTextMessageSentListener getTextMessageSentListener() {
    return textMessageSentListener;
  }

  public void setTextMessageSentListener(WsTextMessageSentListener textMessageSentListener) {
    this.textMessageSentListener = textMessageSentListener;
  }

  public WsBytesMessageSentListener getBytesMessageSentListener() {
    return bytesMessageSentListener;
  }

  public void setBytesMessageSentListener(WsBytesMessageSentListener bytesMessageSentListener) {
    this.bytesMessageSentListener = bytesMessageSentListener;
  }

  public boolean isStompProtocolEnabled() {
    return stompProtocolEnabled;
  }

  public void setCredential(WsConnectionCredential credential) {
    this.credential = credential;
  }

  public WsConnectionCredential getCredential() {
    return credential;
  }

  public WsConnectionMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(WsConnectionMetadata metadata) {
    this.metadata = metadata;
  }

  public WsTextMessageListener getTextMessageListener() {
    return textMessageListener;
  }

  public void setTextMessageListener(WsTextMessageListener textMessageListener) {
    this.textMessageListener = textMessageListener;
  }

  public WsBytesMessageListener getBytesMessageListener() {
    return bytesMessageListener;
  }

  public void setBytesMessageListener(WsBytesMessageListener bytesMessageListener) {
    this.bytesMessageListener = bytesMessageListener;
  }

  public WsConnectedListener getConnectedListener() {
    return connectedListener;
  }

  public void setConnectedListener(WsConnectedListener connectedListener) {
    this.connectedListener = connectedListener;
  }

  public WsDisconnectedListener getDisconnectedListener() {
    return disconnectedListener;
  }

  public void setDisconnectedListener(WsDisconnectedListener disconnectedListener) {
    this.disconnectedListener = disconnectedListener;
  }

  public WsFailureListener getFailureListener() {
    return failureListener;
  }

  public void setFailureListener(WsFailureListener failureListener) {
    this.failureListener = failureListener;
  }

  /**
   * @version 0.0.1
   * @since 0.0.1
   */
  public static class Builder {

    private boolean stompProtocolEnabled;
    private WsConnectionMetadata metadata;
    private WsConnectionCredential credential;
    private WsTextMessageListener textMessageListener;
    private WsTextMessageSentListener textMessageSentListener;
    private WsBytesMessageListener bytesMessageListener;
    private WsBytesMessageSentListener bytesMessageSentListener;
    private WsConnectedListener connectedListener;
    private WsDisconnectedListener disconnectedListener;
    private WsFailureListener failureListener;

    public Builder setBytesMessageSentListener(WsBytesMessageSentListener bytesMessageSentListener) {
      this.bytesMessageSentListener = bytesMessageSentListener;
      return this;
    }

    public Builder setTextMessageSentListener(WsTextMessageSentListener textMessageSentListener) {
      this.textMessageSentListener = textMessageSentListener;
      return this;
    }

    public Builder setStompProtocolEnabled(boolean stompProtocolEnabled) {
      this.stompProtocolEnabled = stompProtocolEnabled;
      return this;
    }

    public Builder setMetadata(WsConnectionMetadata metadata) {
      this.metadata = metadata;
      return this;
    }

    public Builder setCredential(WsConnectionCredential credential) {
      this.credential = credential;
      return this;
    }

    public Builder setTextMessageListener(WsTextMessageListener textMessageListener) {
      this.textMessageListener = textMessageListener;
      return this;
    }

    public Builder setBytesMessageListener(WsBytesMessageListener bytesMessageListener) {
      this.bytesMessageListener = bytesMessageListener;
      return this;
    }

    public Builder setConnectedListener(WsConnectedListener connectedListener) {
      this.connectedListener = connectedListener;
      return this;
    }

    public Builder setDisconnectedListener(WsDisconnectedListener disconnectedListener) {
      this.disconnectedListener = disconnectedListener;
      return this;
    }

    public Builder setFailureListener(WsFailureListener failureListener) {
      this.failureListener = failureListener;
      return this;
    }

    public WsConnectionConfig build() {
      return new WsConnectionConfig(this);
    }
  }
}
