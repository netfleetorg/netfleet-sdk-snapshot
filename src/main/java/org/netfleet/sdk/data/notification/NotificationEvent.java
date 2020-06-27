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
package org.netfleet.sdk.data.notification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class NotificationEvent implements Serializable {

  private Map<String, Object> properties = new HashMap<>();

  private EventDestination source;
  private EventDestination destination;
  private DeliveryMode deliveryMode = DeliveryMode.PERSISTENT;

  private String type;
  private String title;
  private String content;
  private Boolean notified;

  private Long timestamp;

  public NotificationEvent() {
  }

  public NotificationEvent(Builder b) {
    this.source = b.source;
    this.destination = b.destination;
    this.deliveryMode = b.deliveryMode;
    this.properties = b.properties;
    this.timestamp = b.timestamp;
    this.type = b.type;
    this.title = b.title;
    this.content = b.content;
    this.notified = b.notified;
  }

  public EventDestination getSource() {
    return source;
  }

  public void setSource(EventDestination source) {
    this.source = source;
  }

  public EventDestination getDestination() {
    return destination;
  }

  public void setDestination(EventDestination destination) {
    this.destination = destination;
  }

  public void setDeliveryMode(DeliveryMode deliveryMode) {
    this.deliveryMode = deliveryMode;
  }

  public DeliveryMode getDeliveryMode() {
    return deliveryMode;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getNotified() {
    return notified;
  }

  public void setNotified(Boolean notified) {
    this.notified = notified;
  }

  /**
   * @version 1.0.0-RELEASE
   * @since 1.0.0-RELEASE
   */
  public static class Builder {
    private Map<String, Object> properties = new HashMap<>();
    private EventDestination source;
    private EventDestination destination;
    private DeliveryMode deliveryMode = DeliveryMode.PERSISTENT;
    private Long timestamp;
    private String type;
    private String title;
    private String content;
    private Boolean notified;

    public Builder setSource(EventDestination source) {
      this.source = source;
      return this;
    }

    public Builder setDestination(EventDestination destination) {
      this.destination = destination;
      return this;
    }

    public Builder setDeliveryMode(DeliveryMode deliveryMode) {
      this.deliveryMode = deliveryMode;
      return this;
    }

    public Builder setProperties(Map<String, Object> properties) {
      this.properties = properties;
      return this;
    }

    public Builder setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder setType(String type) {
      this.type = type;
      return this;
    }

    public Builder setTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder setContent(String content) {
      this.content = content;
      return this;
    }

    public Builder setNotified(Boolean notified) {
      this.notified = notified;
      return this;
    }

    public NotificationEvent build() {
      return new NotificationEvent(this);
    }
  }
}
