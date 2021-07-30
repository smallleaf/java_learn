package com.share1024.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * \* @Author: yesheng
 * \* Date: 2021/7/30 15:28
 * \* Description:
 * \
 */
public class UdpClient {

    public void run(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
                .handler(new UdpClientHandler());
        Channel channel = bootstrap.bind(9999).sync().channel();
        channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("test".getBytes()),new InetSocketAddress(
                "localhost",port)));
    }

    public static void main(String[] args) throws InterruptedException {
        new UdpClient().run(8999);
    }
}