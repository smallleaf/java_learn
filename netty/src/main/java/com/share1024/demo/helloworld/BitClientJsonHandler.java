package com.share1024.demo.helloworld;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/25 12:26
 * \* Description:
 * \
 */
public class BitClientJsonHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====channelRegistered");
        super.channelRegistered(ctx);
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyData myData = new MyData();
        myData.setUserName("1024bit");
        myData.setAge(18);
        ChannelFuture channelFuture  = ctx.writeAndFlush(myData);
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                System.out.println("actice success===="+future.isSuccess());
            }
        });
        System.out.println("====1");
        super.channelActive(ctx);
    }
}