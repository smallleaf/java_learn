package com.share1024.demo.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.base64.Base64Encoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/16 16:15
 * \* Description:
 * \
 */
public class BitServer {

    public void bind(int port){

        //两个NIO线程池，每一个NioEventLoopGroup会生成多个NioEventLoop
        //，每个NIoEventLoop对应一个Reactor线程

        //Boss线程用来接收客户端TCP连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        //work线程用来处理I/O相关的读写操作，或者执行系统Task、定时任务等等
        EventLoopGroup workGroup = new NioEventLoopGroup();

        //启动辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                //设置Channel处理类型为NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                //配置tcp参数
                .option(ChannelOption.SO_BACKLOG,1024)
                //设置I/O事件处理类
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //添加事件处理
                        ch.pipeline().addLast(new JsonDecoder());
                        ch.pipeline().addLast(new JsonEncoder());
                        ch.pipeline().addLast(new Base64Encoder());
                        ch.pipeline().addLast(new Base64Decoder());
                        ch.pipeline().addLast(new BitServerJsonHandler());
                    }
                });
        try {
            //绑定端口等待服务启动完毕
            ChannelFuture ch = bootstrap.bind(port).sync();
            ch.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    System.out.println("+=1");
                }
            });
            System.out.println("Bit Server start port :" + port);
            //阻塞，等待服务端链路关闭之后main才能退出，服务在此就已经启动。
            ch.channel().closeFuture().sync();
            System.out.println("===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //NIO线程池优雅退出，释放相关的资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new BitServer().bind(8080);
    }
}