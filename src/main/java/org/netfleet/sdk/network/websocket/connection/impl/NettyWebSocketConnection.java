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

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.util.concurrent.FutureListener;
import org.netfleet.sdk.network.websocket.WsRuntimeException;
import org.netfleet.sdk.network.websocket.connection.*;
import org.netfleet.sdk.network.websocket.event.WsConnectedEvent;
import org.netfleet.sdk.network.websocket.event.WsConnectedListener;
import org.netfleet.sdk.network.websocket.event.impl.WsConnectedEventImpl;
import org.netfleet.sdk.network.websocket.stomp.StompCommand;
import org.netfleet.sdk.network.websocket.stomp.StompFrame;
import org.netfleet.sdk.network.websocket.stomp.StompHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class NettyWebSocketConnection implements WsConnection {

  private final Logger log = LoggerFactory.getLogger(NettyWebSocketConnection.class);

  private static final WebSocketVersion WEB_SOCKET_VERSION = WebSocketVersion.V13;
  private static final int MAX_LENGTH = 16 * 1024 * 1024; // 16 MB

  private final AtomicReference<WsConnectionState> state
    = new AtomicReference<>(WsConnectionState.INITIAL);

  private final String id = UUID.randomUUID().toString();

  private final boolean stompProtocolEnabled;

  private final WsConnectionMetadata metadata;
  private final WsConnectionCredential credential;

  private final WsConnectedListener connectedListener;

  private final NettyInboundHandler inboundHandler;
  private final NettyInitializer initializer;

  private EventLoopGroup eventLoopGroup;
  private Bootstrap bootstrap;
  private ChannelFuture channelFuture;

  public NettyWebSocketConnection(WsConnectionConfig cfg) {
    this.stompProtocolEnabled = cfg.isStompProtocolEnabled();
    this.connectedListener = cfg.getConnectedListener();
    this.metadata = cfg.getMetadata();
    this.credential = cfg.getCredential();

    this.inboundHandler = new NettyInboundHandler(cfg.isStompProtocolEnabled(),
      WebSocketClientHandshakerFactory.newHandshaker(
        metadata.getUri(),
        WEB_SOCKET_VERSION,
        null,
        true,
        new DefaultHttpHeaders()),
      cfg.getTextMessageListener(),
      cfg.getBytesMessageListener(),
      cfg.getDisconnectedListener(),
      cfg.getFailureListener());

    NettyOutboundHandlerAdapter outboundHandler
      = new NettyOutboundHandlerAdapter(cfg.getTextMessageSentListener(),
      cfg.getBytesMessageSentListener(),
      cfg.getFailureListener());

    LinkedHashMap<String, ChannelHandler> lhm = new LinkedHashMap<>();
    lhm.put("http-codec", new HttpClientCodec());
    lhm.put("decoder", new HttpRequestDecoder());
    lhm.put("aggregator", new HttpObjectAggregator(MAX_LENGTH));
    lhm.put("encoder", new HttpResponseEncoder());

    this.initializer = new NettyInitializer(inboundHandler, outboundHandler, lhm);
  }

  private void checkpoint(WsConnectionState cs) {
    log.info("Connection status has changed from {} to {}.", state.getAndSet(cs), state.get());
  }

  @Override
  public void close() {
    stop();
  }

  /**
   * @version 1.0.0-RELEASE
   * @since 1.0.0-RELEASE
   */
  private class ConnectedFutureListener implements FutureListener<Object> {

    @Override
    public void operationComplete(io.netty.util.concurrent.Future<Object> future) {
      if (future.isSuccess()) {
        if (NettyWebSocketConnection.this.connectedListener != null) {
          WsConnectedEvent event = new WsConnectedEventImpl();
          NettyWebSocketConnection.this.connectedListener.onConnect(event);
        }

        checkpoint(WsConnectionState.CONNECTED);
      } else {
        if (channelFuture != null) {
          Throwable throwable = channelFuture.cause();
          log.error("Connection cannot be established, exception: {}", throwable);
        }

        if (channelFuture != null && channelFuture.channel() != null) {
          channelFuture.channel().close();
          checkpoint(WsConnectionState.DISCONNECTED);
        } else {
          throw new WsRuntimeException("Channel is null. This should have never happened. errcode=102");
        }
      }
    }
  }

  @Override
  public void start() throws WsRuntimeException {
    eventLoopGroup = new NioEventLoopGroup();
    bootstrap = new Bootstrap();

    try {
      bootstrap.group(eventLoopGroup);
      bootstrap.channel(NioSocketChannel.class);
      bootstrap.handler(initializer);

      channelFuture = bootstrap.connect(
        getMetadata().getUri().getHost(),
        getMetadata().getPort())
        .sync();

      inboundHandler.handshakeFuture().sync();

      if (stompProtocolEnabled) {
        StompFrame frame = new StompFrame();
        frame.setCommand(StompCommand.CONNECT);
        frame.getHeader().addHeader(StompHeaders.LOGIN, credential.getUsername());
        frame.getHeader().addHeader(StompHeaders.PASSCODE, credential.getPassword());

        channelFuture.channel()
          .writeAndFlush(frame)
          .addListener(new ConnectedFutureListener())
          .syncUninterruptibly();
      }
    } catch (InterruptedException e) {
      throw new WsRuntimeException(e);
    }
  }

  @Override
  public void stop() throws WsRuntimeException {
    channelFuture.channel().writeAndFlush(new CloseWebSocketFrame());
    try {
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      log.error("", e);
    }

    eventLoopGroup.shutdownGracefully();

    eventLoopGroup = null;
    channelFuture = null;
    bootstrap = null;
  }

  @Override
  public void dispatch(final Object message) {
    if (channelFuture == null) {
      throw new WsRuntimeException("Connection not established, message cannot be sent.");
    }

    channelFuture.channel().writeAndFlush(message);
  }

  @Override
  public Future<Object> dispatchAsync(Object message) {
    if (channelFuture == null) {
      throw new WsRuntimeException("Connection not established.");
    }

    ChannelFuture cf = channelFuture.channel().writeAndFlush(message);
    Future f = cf;
    return f;
  }

  @Override
  public boolean isConnected() {
    return channelFuture != null && state.get().equals(WsConnectionState.CONNECTED);
  }

  @Override
  public WsConnectionMetadata getMetadata() {
    return metadata;
  }

  @Override
  public String getId() {
    return id;
  }
}
