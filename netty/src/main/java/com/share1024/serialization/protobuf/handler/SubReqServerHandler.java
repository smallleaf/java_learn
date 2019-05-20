package com.share1024.serialization.protobuf.handler;

import com.share1024.serialization.protobuf.pb.SubScribeReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubScribeReq.SubReq req = (SubScribeReq.SubReq) msg;
        System.out.println("from client:"+msg);
        SubScribeReq.SubReq.Builder builder = SubScribeReq.SubReq.newBuilder();
        builder.setAddress(req.getAddress());
        builder.setProductName(req.getProductName());
        builder.setSubReqId(req.getSubReqId());
        builder.setUsername(req.getUsername());
        ctx.writeAndFlush(builder.build());
    }


}
