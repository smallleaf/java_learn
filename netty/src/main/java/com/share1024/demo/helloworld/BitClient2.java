package com.share1024.demo.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.base64.Base64Encoder;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/16 16:42
 * \* Description:
 * \
 */
public class BitClient2 {

    public void connect(String host,int port){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new JsonDecoder());
                            ch.pipeline().addLast(new JsonEncoder());
                            ch.pipeline().addLast(new Base64Encoder());
                            ch.pipeline().addLast(new Base64Decoder());
                            ch.pipeline().addLast(new BitClientJsonHandler());
                    }
                });
        try {
            ChannelFuture ch = bootstrap.connect(host, port).sync();
            ch.addListener(future -> {
                if(future.isSuccess()){
                    System.out.println("connect success");
                }else{
                    System.out.println("connect fail");
                }
            });
            ch.channel().eventLoop().execute(()->{
                System.out.println("====aaa===="+Thread.currentThread().getName());
            });
            System.out.println("=====ccc===="+Thread.currentThread().getName());
            System.out.println("client start success");
            ch.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new BitClient2().connect("localhost",8080);
    }
}