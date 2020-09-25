package com.share1024.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/25 15:47
 * \* Description:
 * \
 */
public class BitClientHandler extends ChannelInboundHandlerAdapter {

    private static final String REQ = "REQ";
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = null;
        for (int i = 0; i < 100; i++) {
            buf = Unpooled.buffer(REQ.getBytes().length);
            buf.writeBytes(REQ.getBytes());
            ctx.writeAndFlush(buf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println(body);
        super.channelRead(ctx, msg);
    }
}