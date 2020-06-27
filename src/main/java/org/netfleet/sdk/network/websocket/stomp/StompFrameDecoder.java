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

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class StompFrameDecoder {

  private StompCommand findCommand(final String command) {
    StompCommand cmd = null;

    for (StompCommand value : StompCommand.values()) {
      if (command.equalsIgnoreCase(value.name())) {
        cmd = value;
        break;
      }
    }

    return cmd;
  }

  public StompFrame decodeAsStompFrame(final String m) {
    String[] lines = m.split("\n");
    String command = lines[0].trim();

    StompCommand cmd = findCommand(command);
    if (cmd == null) {
      throw new IllegalStateException("Illegal Stomp Command: " + command);
    }

    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    int i = 1;
    for (; i < lines.length; ++i) {
      String line = lines[i].trim();
      if (line.equals("")) {
        break;
      }

      String[] parts = line.split(":");
      String name = parts[0].trim();
      String value = "";
      if (parts.length == 2) {
        value = parts[1].trim();
      }

      map.put(name, value);
    }

    StringBuilder sb = new StringBuilder();
    for (; i < lines.length; ++i) {
      sb.append(lines[i]);
    }

    String content = sb.toString().trim();
    StompFrame frame = new StompFrame();
    frame.setCommand(cmd);
    frame.setContent(content);

    for (Map.Entry<String, Object> entry : map.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();

      frame.getHeader().addHeader(key, value.toString());
    }

    return frame;
  }

  public StompFrame decodeFromTextWebSocketFrame(final TextWebSocketFrame m) {
    return decodeAsStompFrame(m.text());
  }

  public StompFrame decodeFromBinaryWebSocketFrame(final BinaryWebSocketFrame m) {
    ByteBuf buf = m.content();
    String string = buf.toString(StandardCharsets.UTF_8);

    return decodeAsStompFrame(string);
  }

}
