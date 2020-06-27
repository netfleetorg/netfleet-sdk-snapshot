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

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class NettyInitializer extends ChannelInitializer {

  private final NettyInboundHandler inboundHandler;
  private final NettyOutboundHandlerAdapter outboundHandler;
  private final Map<String, ChannelHandler> handlerMap;

  public NettyInitializer(NettyInboundHandler inboundHandler,
                          NettyOutboundHandlerAdapter outboundHandler,
                          Map<String, ChannelHandler> handlerMap) {
    this.inboundHandler = inboundHandler;
    this.outboundHandler = outboundHandler;
    this.handlerMap = handlerMap;
  }

  /**
   * This method will be called once the {@link Channel} was registered. After
   * the method returns this instance will be removed from
   * the {@link ChannelPipeline} of the {@link Channel}.
   *
   * @param ch the {@link Channel} which was registered.
   * @throws Exception is thrown if an error occurs. In that case it will be handled by
   *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)}
   *                   which will by default close the {@link Channel}.
   */
  @Override
  protected void initChannel(Channel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    for (Map.Entry<String, ChannelHandler> entry : handlerMap.entrySet()) {
      String k = entry.getKey();
      ChannelHandler v = entry.getValue();

      pipeline.addLast(k, v);
    }

    pipeline.addLast(outboundHandler);
    pipeline.addLast(inboundHandler);
  }

}
