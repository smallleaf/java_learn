package com.share1024.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * \* @Author: yesheng
 * \* Date: 2021/7/30 15:21
 * \* Description:
 * \
 */
public class UdpServer {

    public void run(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
                .handler(new UdpServerHandler());
        bootstrap.bind(port).sync().channel().closeFuture().await();
    }

    public static void main(String[] args) throws InterruptedException {
        new UdpServer().run(8999);
    }
}