package com.share1024.keepalive;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ConnectionListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if(!future.isSuccess()){
            System.out.println("重新连接");
            
        }
    }
}
