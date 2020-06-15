package com.share1024.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* @Author: yesheng
 * \* Date: 2020/5/18 22:07
 * \* Description:
 * \
 */
public class CustomHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       ctx.fireChannelActive();
    }
}