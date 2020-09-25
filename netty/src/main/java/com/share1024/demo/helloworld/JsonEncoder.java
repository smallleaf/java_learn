package com.share1024.demo.helloworld;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/25 12:21
 * \* Description:
 * \
 */
public class JsonEncoder extends MessageToByteEncoder<Object>{
    Gson gson = new Gson();
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        out.writeBytes(gson.toJson(msg).getBytes());
    }
}