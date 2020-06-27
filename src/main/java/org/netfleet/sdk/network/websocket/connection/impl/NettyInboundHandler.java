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
package org.netfleet.sdk.network.websocket.connection.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import org.netfleet.sdk.network.websocket.event.*;
import org.netfleet.sdk.network.websocket.event.impl.WsBytesMessageEventImpl;
import org.netfleet.sdk.network.websocket.event.impl.WsDisconnectedEventImpl;
import org.netfleet.sdk.network.websocket.event.impl.WsFailureEventImpl;
import org.netfleet.sdk.network.websocket.event.impl.WsTextMessageEventImpl;
import org.netfleet.sdk.network.websocket.stomp.StompFrame;
import org.netfleet.sdk.network.websocket.stomp.StompFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
@ChannelHandler.Sharable
public class NettyInboundHandler extends SimpleChannelInboundHandler {

  private final Logger log = LoggerFactory.getLogger(NettyInboundHandler.class);

  private boolean stompProtocolEnabled;

  private WebSocketClientHandshaker handshaker;
  private WsTextMessageListener textMessageListener;
  private WsBytesMessageListener bytesMessageListener;
  private WsDisconnectedListener disconnectedListener;
  private WsFailureListener failureListener;

  private ChannelPromise handshakeFuture;

  public NettyInboundHandler(boolean stompProtocolEnabled,
                             WebSocketClientHandshaker handshaker,
                             WsTextMessageListener textMessageListener,
                             WsBytesMessageListener bytesMessageListener,
                             WsDisconnectedListener disconnectedListener,
                             WsFailureListener failureListener) {
    super();
    this.stompProtocolEnabled = stompProtocolEnabled;
    this.handshaker = handshaker;
    this.textMessageListener = textMessageListener;
    this.bytesMessageListener = bytesMessageListener;
    this.disconnectedListener = disconnectedListener;
    this.failureListener = failureListener;
  }

  public ChannelFuture handshakeFuture() {
    return handshakeFuture;
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    handshakeFuture = ctx.newPromise();
    log.info("A new handler added to current channel.");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    handshaker.handshake(ctx.channel());
    log.info("Channel active...");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("Channel in-active...");
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
    if (!handshakeFuture.isDone()) {
      handshakeFuture.setFailure(cause);
    }

    if (failureListener != null) {
      WsFailureEvent event = new WsFailureEventImpl(cause);
      failureListener.onFailure(event);
    }

    log.info("Exception caught: {}.", cause);
    ctx.close();
  }

  /**
   * <strong>Please keep in mind that this method will be renamed to
   * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
   * <p>
   * Is called for each message of type Object.
   *
   * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
   *            belongs to
   * @param msg the message to handle
   * @throws Exception is thrown if an error occurred
   */
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    Channel ch = ctx.channel();

    if (!handshaker.isHandshakeComplete()) {
      try {
        handshaker.finishHandshake(ch, (FullHttpResponse) msg);
        log.info("Connection established, handshake message: {}. \n", msg.toString());
        handshakeFuture.setSuccess();
      } catch (WebSocketHandshakeException e) {
        log.info("Handshake error: {}.", e.getMessage());
        handshakeFuture.setFailure(e);
      }

      return;
    }

    if (msg instanceof WebSocketFrame) {
      if (msg instanceof TextWebSocketFrame) {
        handleTextMessage((TextWebSocketFrame) msg);
      } else if (msg instanceof BinaryWebSocketFrame) {
        handleBinaryMessage((BinaryWebSocketFrame) msg);
      } else if (msg instanceof CloseWebSocketFrame) {
        handleCloseFrame((CloseWebSocketFrame) msg);
      } else if (msg instanceof PongWebSocketFrame) {
        handlePongFrame((PongWebSocketFrame) msg);
      } else {
        WebSocketFrame f = (WebSocketFrame) msg;
        log.info("Unknown WebSocketFrame received: {}", f);
      }
    }
  }

  private void handleTextMessage(TextWebSocketFrame frame) {
    String text = frame.text();
    String message;

    if (stompProtocolEnabled) {
      StompFrameDecoder decoder = new StompFrameDecoder();
      StompFrame stompFrame = decoder.decodeAsStompFrame(text);

      message = stompFrame.getContent();
    } else {
      message = text;
    }

    WsTextMessageEvent event = new WsTextMessageEventImpl(message);
    textMessageListener.onMessage(event);

    log.info("Message Received, length: {}", text.length());
  }

  private void handleBinaryMessage(BinaryWebSocketFrame frame) {
    ByteBuf buf = frame.content();
    byte[] bytes;

    if (stompProtocolEnabled) {
      StompFrameDecoder decoder = new StompFrameDecoder();
      StompFrame stompFrame = decoder.decodeFromBinaryWebSocketFrame(frame);

      bytes = stompFrame.getContent().getBytes(StandardCharsets.UTF_8);
    } else {
      bytes = buf.array();
    }

    WsBytesMessageEvent event = new WsBytesMessageEventImpl(bytes);
    bytesMessageListener.onMessage(event);

    log.info("Message received, length: {}", bytes.length);
  }

  private void handleCloseFrame(CloseWebSocketFrame frame) {
    if (disconnectedListener != null) {
      String phrase = frame.reasonText();
      int status = frame.statusCode();

      WsDisconnectedEvent event = new WsDisconnectedEventImpl(phrase, status);
      disconnectedListener.onDisconnected(event);
    }

    log.info("Disconnecting frame received with phrase {} and status {} ",
      frame.reasonText(),
      frame.statusCode());
  }

  private void handlePongFrame(PongWebSocketFrame frame) {
    log.info("Pong received.");
  }
}
