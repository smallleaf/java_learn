package com.share1024.serialization.protobuf.handler;

import com.share1024.serialization.protobuf.pb.SubScribeReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0 ;i<100;i++){
            SubScribeReq.SubReq.Builder builder = SubScribeReq.SubReq.newBuilder();
            builder.setUsername("hhhsdd");
            builder.setSubReqId(i);
            builder.setProductName("哈哈哈");
            builder.setAddress("address");
            ctx.write(builder);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("from server:"+msg);
    }
}
