package com.share1024.demo.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/16 16:44
 * \* Description:
 * \
 */
public class BitClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String data = "hello server i am bit client 1";
        ByteBuf byteBuf = Unpooled.wrappedBuffer(data.getBytes());
        ctx.writeAndFlush(byteBuf);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        if(buf != null){
            byte[] readBytes = new byte[buf.readableBytes()];
            buf.readBytes(readBytes);
            String data = new String(readBytes,"utf-8");
            System.out.println("receive server data :" +data);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client read complete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=============channelInactive===========");
        super.channelInactive(ctx);
    }
}