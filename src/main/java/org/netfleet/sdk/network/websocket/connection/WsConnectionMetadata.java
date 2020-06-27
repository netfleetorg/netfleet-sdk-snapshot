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

import java.io.Serializable;
import java.net.Proxy;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class WsConnectionMetadata implements Serializable {

  private boolean retryOnConnectionFailure = true;
  private Map.Entry<Long, TimeUnit> pingInterval;
  private Map.Entry<Long, TimeUnit> readTimeout;
  private int timeout;
  private Proxy proxy;
  private String host;
  private URI uri;
  private int port;

  public WsConnectionMetadata() {
  }

  public WsConnectionMetadata(Builder b) {
    this(b.host, b.uri, b.port, b.timeout, b.proxy, b.pingInterval, b.readTimeout, b.retryOnConnectionFailure);
  }

  private WsConnectionMetadata(String host, URI uri, int port, int timeout, Proxy proxy,
                               Map.Entry<Long, TimeUnit> pingInterval, Map.Entry<Long, TimeUnit> readTimeout,
                               boolean retryOnConnectionFailure) {
    this.host = host;
    this.uri = uri;
    this.port = port;
    this.timeout = timeout;
    this.proxy = proxy;
    this.pingInterval = pingInterval;
    this.readTimeout = readTimeout;
    this.retryOnConnectionFailure = retryOnConnectionFailure;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public Proxy getProxy() {
    return proxy;
  }

  public void setProxy(Proxy proxy) {
    this.proxy = proxy;
  }

  public Map.Entry<Long, TimeUnit> getPingInterval() {
    return pingInterval;
  }

  public void setPingInterval(Map.Entry<Long, TimeUnit> pingInterval) {
    this.pingInterval = pingInterval;
  }

  public Map.Entry<Long, TimeUnit> getReadTimeout() {
    return readTimeout;
  }

  public void setReadTimeout(Map.Entry<Long, TimeUnit> readTimeout) {
    this.readTimeout = readTimeout;
  }

  public boolean isRetryOnConnectionFailure() {
    return retryOnConnectionFailure;
  }

  public void setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
    this.retryOnConnectionFailure = retryOnConnectionFailure;
  }

  /**
   * @version 0.0.1
   * @since 0.0.1
   */
  public static class Builder {

    private String host;
    private URI uri;
    private int port;
    private int timeout;
    private Proxy proxy;
    private Map.Entry<Long, TimeUnit> pingInterval;
    private Map.Entry<Long, TimeUnit> readTimeout;
    private boolean retryOnConnectionFailure = true;

    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    public Builder setUri(URI uri) {
      this.uri = uri;
      return this;
    }

    public Builder setPort(int port) {
      this.port = port;
      return this;
    }

    public Builder setTimeout(int timeout) {
      this.timeout = timeout;
      return this;
    }

    public Builder setProxy(Proxy proxy) {
      this.proxy = proxy;
      return this;
    }

    public Builder setPingInterval(Map.Entry<Long, TimeUnit> pingInterval) {
      this.pingInterval = pingInterval;
      return this;
    }

    public Builder setReadTimeout(Map.Entry<Long, TimeUnit> readTimeout) {
      this.readTimeout = readTimeout;
      return this;
    }

    public Builder setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
      this.retryOnConnectionFailure = retryOnConnectionFailure;
      return this;
    }

    public WsConnectionMetadata build() {
      return new WsConnectionMetadata(this);
    }
  }

}
