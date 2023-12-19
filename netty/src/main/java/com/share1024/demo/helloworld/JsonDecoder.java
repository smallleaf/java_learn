package com.share1024.demo.helloworld;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/25 12:12
 * \* Description:
 * \
 */
public class JsonDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        System.out.println(new String(data,"utf-8"));
        out.add(new String(data,"utf-8"));
    }
}