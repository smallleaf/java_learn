package com.share1024.keepalive.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("===============================");
        socketChannel.pipeline().addLast(new TimeServerHandler())
                .addLast(new IdleStateHandler(5,0,0))
                .addLast(new HeartbeatServerHandler())
                .addLast(new TimeServerHandler());
    }
}
