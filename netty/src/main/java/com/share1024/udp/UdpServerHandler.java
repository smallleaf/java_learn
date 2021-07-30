package com.share1024.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;


/**
 * \* @Author: yesheng
 * \* Date: 2021/7/30 15:23
 * \* Description:
 * \
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    InetSocketAddress sender;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===active===");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        if(sender == null){
            sender = msg.sender();
        }
        byte[] bytes = new byte[msg.content().readableBytes()];
        msg.content().readBytes(bytes);
        System.out.println("re "+new String(bytes));
        String content = "send msg " + System.currentTimeMillis();
        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(content.getBytes()),sender));
    }
}