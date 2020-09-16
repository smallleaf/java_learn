package com.share1024.demo.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/16 16:21
 * \* Description:
 * \
 */
public class BitServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf data = (ByteBuf) msg;
        if(data != null){
            byte[] readBytes = new byte[data.readableBytes()];
            data.readBytes(readBytes);
            String result = new String(readBytes,"utf-8");
            System.out.println("receive client data : "+result);
            String responseData = "ok";
            ByteBuf responseBuf = Unpooled.copiedBuffer(responseData.getBytes());
            ctx.writeAndFlush(responseBuf);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server read complete");
        super.channelReadComplete(ctx);
    }
}