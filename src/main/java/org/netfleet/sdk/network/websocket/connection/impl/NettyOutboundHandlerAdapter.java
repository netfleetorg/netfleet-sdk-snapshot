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
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.netfleet.sdk.network.websocket.WsRuntimeException;
import org.netfleet.sdk.network.websocket.event.*;
import org.netfleet.sdk.network.websocket.event.impl.WsBytesMessageSentEventImpl;
import org.netfleet.sdk.network.websocket.event.impl.WsFailureEventImpl;
import org.netfleet.sdk.network.websocket.event.impl.WsTextMessageSentEventImpl;
import org.netfleet.sdk.network.websocket.stomp.StompCommand;
import org.netfleet.sdk.network.websocket.stomp.StompFrame;
import org.netfleet.sdk.network.websocket.stomp.StompFrameEncoder;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class NettyOutboundHandlerAdapter extends ChannelOutboundHandlerAdapter {

  private final WsTextMessageSentListener textMessageSentListener;
  private final WsBytesMessageSentListener bytesMessageSentListener;
  private final WsFailureListener failureListener;

  public NettyOutboundHandlerAdapter(WsTextMessageSentListener textMessageSentListener,
                                     WsBytesMessageSentListener bytesMessageSentListener,
                                     WsFailureListener failureListener) {
    this.textMessageSentListener = textMessageSentListener;
    this.bytesMessageSentListener = bytesMessageSentListener;
    this.failureListener = failureListener;
  }

  /**
   * Calls {@link ChannelHandlerContext#write(Object, ChannelPromise)} to forward
   * to the next {@link ChannelOutboundHandler} in the {@link ChannelPipeline}.
   * <p>
   * Sub-classes may override this method to change behavior.
   *
   * @param ctx
   * @param msg
   * @param promise
   * @throws java.lang.Exception
   */
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

    if (msg instanceof StompFrame)
      handleStompMessage(ctx, (StompFrame) msg, promise);
    else if (msg instanceof String)
      handleTextMessage(ctx, (String) msg, promise);
    else if (msg instanceof byte[])
      handleBinaryMessage(ctx, (byte[]) msg, promise);
    else
      super.write(ctx, msg, promise);

  }

  private WsFailureEvent failureEvent() {
    return new WsFailureEventImpl(new WsRuntimeException("Message cannot be sent."));
  }

  private WsTextMessageSentEvent textMessageSentEvent(String message) {
    return new WsTextMessageSentEventImpl(message);
  }

  private WsBytesMessageSentEvent bytesMessageSent(byte[] message) {
    return new WsBytesMessageSentEventImpl(message);
  }

  private void handleStompMessage(ChannelHandlerContext ctx, final StompFrame msg, ChannelPromise promise) throws InterruptedException {
    StompFrameEncoder encoder = new StompFrameEncoder();

    TextWebSocketFrame textWebSocketFrame = encoder.encodeAsTextWebSocketFrame((StompFrame) msg);

    ChannelFuture channelFuture = ctx.writeAndFlush(textWebSocketFrame);
    if (promise != null)
      promise.setSuccess();

    if (msg.getCommand().equals(StompCommand.MESSAGE) || msg.getCommand().equals(StompCommand.SEND)) {
      channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
        @Override
        public void operationComplete(Future<? super Void> future) throws Exception {
          if (future.isSuccess())
            textMessageSentListener.onSent(textMessageSentEvent(msg.getContent()));
          else
            failureListener.onFailure(failureEvent());
        }
      }).sync();
    }

  }

  private void handleTextMessage(ChannelHandlerContext ctx, final String msg, ChannelPromise promise) throws InterruptedException {
    TextWebSocketFrame webSocketFrame = new TextWebSocketFrame(msg);

    ChannelFuture channelFuture = ctx.writeAndFlush(webSocketFrame);
    if (promise != null)
      promise.setSuccess();

    channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
      @Override
      public void operationComplete(Future<? super Void> future) throws Exception {
        if (future.isSuccess())
          textMessageSentListener.onSent(textMessageSentEvent(msg));
        else
          failureListener.onFailure(failureEvent());
      }
    }).sync();

  }

  private void handleBinaryMessage(ChannelHandlerContext ctx, final byte[] msg, ChannelPromise promise) throws InterruptedException {
    ByteBuf buf = Unpooled.copiedBuffer(msg);

    BinaryWebSocketFrame webSocketFrame = new BinaryWebSocketFrame(buf);

    ChannelFuture channelFuture = ctx.writeAndFlush(msg);
    if (promise != null)
      promise.setSuccess();

    channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
      @Override
      public void operationComplete(Future<? super Void> future) throws Exception {
        if (future.isSuccess())
          bytesMessageSentListener.onSent(bytesMessageSent(msg));
        else
          failureListener.onFailure(failureEvent());
      }
    }).sync();

  }

}
