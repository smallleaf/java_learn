package com.share1024.keepalive.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if(evt instanceof IdleStateEvent){
            IdleStateEvent stat = (IdleStateEvent) evt;
            if(stat.state() == IdleState.ALL_IDLE){
                System.out.println("all idle");
            }else if (stat.state() == IdleState.READER_IDLE){
                System.out.println("reader idel");
                ctx.writeAndFlush(Unpooled.copiedBuffer("server try ping...".getBytes()));
            }else if (stat.state() == IdleState.WRITER_IDLE){
                System.out.println("writer idel");
            }else{
                System.out.println("nothing");
            }
        }
    }
}
