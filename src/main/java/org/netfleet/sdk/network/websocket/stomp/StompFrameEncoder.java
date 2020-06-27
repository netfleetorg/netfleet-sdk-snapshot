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
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.netfleet.sdk.network.websocket.WsRuntimeException;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class StompFrameEncoder {

  private static final List<StompCommand> ACCEPTS_BODY = new LinkedList<StompCommand>() {{
    add(StompCommand.SEND);
    add(StompCommand.MESSAGE);
    add(StompCommand.UNKNOWN);
    add(StompCommand.ERROR);
  }};

  private static final String NULL = "\0";
  private static final String NEXT_LINE = "\n";

  private boolean containsBody(final StompFrame frame) {
    if (frame.getCommand() == null) {
      throw new WsRuntimeException("StompFrame doesn't have command property.");
    }

    StompCommand command = frame.getCommand();
    String cmdStr = command.toString();

    for (StompCommand cmd : ACCEPTS_BODY) {
      String next = cmd.toString();

      if (cmdStr.equalsIgnoreCase(next)) {
        return true;
      }
    }

    return false;
  }

  public String encodeAsString(final StompFrame frame) {
    if (frame.getCommand() == null) {
      throw new WsRuntimeException("StompFrame doesn't have command property.");
    }

    StringBuilder buffer = new StringBuilder();
    buffer.append(frame.getCommand());
    buffer.append(NEXT_LINE);

    StompHeader header = frame.getHeader();
    Iterator<Map.Entry<String, String>> iterator = header.iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();

      buffer.append(entry.getKey());
      buffer.append(":");
      buffer.append(entry.getValue());
      buffer.append(NEXT_LINE);
    }

    buffer.append(NEXT_LINE);
    if (containsBody(frame)) {
      buffer.append(frame.getContent());
    }

    buffer.append(NULL);
    return buffer.toString();
  }

  public TextWebSocketFrame encodeAsTextWebSocketFrame(final StompFrame frame) {
    return new TextWebSocketFrame(encodeAsString(frame));
  }

  public BinaryWebSocketFrame encodeAsBinaryWebSocketFrame(final StompFrame frame) {
    String encodedAsString = encodeAsString(frame);
    byte[] encodedAsBinary = encodedAsString.getBytes(StandardCharsets.UTF_8);

    ByteBuf buf = Unpooled.copiedBuffer(encodedAsBinary);
    return new BinaryWebSocketFrame(buf);
  }
}
