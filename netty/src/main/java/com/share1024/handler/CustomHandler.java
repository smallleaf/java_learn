package com.share1024.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * \* @Author: yesheng
 * \* Date: 2020/5/18 22:07
 * \* Description:
 * \
 */
public class CustomHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       ctx.fireChannelActive();
    }

    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        Stream stream = test.stream();
        System.out.println("===");
    }
}