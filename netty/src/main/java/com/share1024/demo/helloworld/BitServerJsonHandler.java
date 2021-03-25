package com.share1024.demo.helloworld;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/25 12:28
 * \* Description:
 * \
 */
public class BitServerJsonHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===channelRegistered===");
        super.channelRegistered(ctx);
    }




    Gson gson = new Gson();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            MyData myData = gson.fromJson(String.valueOf(msg),MyData.class);
            System.out.println(myData.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        super.channelRead(ctx, msg);
    }
}