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

import org.netfleet.sdk.network.websocket.WsRuntimeException;

import java.io.Serializable;
import java.util.*;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class StompHeader implements Serializable, Iterable<Map.Entry<String, String>> {

  private static final Set<String> acceptedHeaderKeys;

  static {
    acceptedHeaderKeys = new HashSet<>();
    acceptedHeaderKeys.add(StompHeaders.CONTENT_LENGTH.toString());
    acceptedHeaderKeys.add(StompHeaders.CONTENT_TYPE.toString());
    acceptedHeaderKeys.add(StompHeaders.HOST.toString());
    acceptedHeaderKeys.add(StompHeaders.VERSION.toString());
    acceptedHeaderKeys.add(StompHeaders.ACCEPT_VERSION.toString());
    acceptedHeaderKeys.add(StompHeaders.SESSION.toString());
    acceptedHeaderKeys.add(StompHeaders.SERVER.toString());
    acceptedHeaderKeys.add(StompHeaders.LOGIN.toString());
    acceptedHeaderKeys.add(StompHeaders.PASSCODE.toString());
    acceptedHeaderKeys.add(StompHeaders.HEARTBEAT.toString());
    acceptedHeaderKeys.add(StompHeaders.DESTINATION.toString());
    acceptedHeaderKeys.add(StompHeaders.RECEIPT.toString());
    acceptedHeaderKeys.add(StompHeaders.RECEIPT_ID.toString());
    acceptedHeaderKeys.add(StompHeaders.ACK.toString());
    acceptedHeaderKeys.add(StompHeaders.ID.toString());
    acceptedHeaderKeys.add(StompHeaders.SUBSCRIPTION.toString());
    acceptedHeaderKeys.add(StompHeaders.MESSAGE_ID.toString());
    acceptedHeaderKeys.add(StompHeaders.TRANSACTION.toString());
    acceptedHeaderKeys.add(StompHeaders.MESSAGE.toString());
  }

  private final Map<String, String> headers = new HashMap<>();

  private void checkHeaderKey(String key) {
    if (!acceptedHeaderKeys.contains(key)) {
      throw new WsRuntimeException("Given key is not acceptable type of header. " +
        "See: 'https://stomp.github.io/stomp-specification-1.2.html#Frames_and_Headers'");
    }
  }

  public void addHeader(StompHeaders key, String value) {
    addHeader(key.toString(), value);
  }

  public void addHeader(String key, String value) {
    checkHeaderKey(key);

    if (value == null) {
      throw new WsRuntimeException("Null values as header value is not acceptable.");
    }

    headers.put(key, value);
  }

  public String removeHeader(String key) {
    checkHeaderKey(key);

    return headers.remove(key);
  }

  public boolean containsHeader(String key) {
    checkHeaderKey(key);

    return headers.containsKey(key);
  }

  @Override
  public Iterator<Map.Entry<String, String>> iterator() {
    return headers.entrySet().iterator();
  }

}
