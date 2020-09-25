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