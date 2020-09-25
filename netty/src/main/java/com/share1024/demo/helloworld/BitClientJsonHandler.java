package com.share1024.demo.helloworld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/25 12:26
 * \* Description:
 * \
 */
public class BitClientJsonHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyData myData = new MyData();
        myData.setUserName("1024bit");
        myData.setAge(18);
        ctx.writeAndFlush(myData);
        super.channelActive(ctx);
    }
}