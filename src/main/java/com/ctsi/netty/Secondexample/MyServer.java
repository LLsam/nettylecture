package com.ctsi.netty.Secondexample;

import com.ctsi.netty.Firstexample.TestServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//获取连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//连接处理

        try {
            ServerBootstrap serverbootstrap = new ServerBootstrap();
            serverbootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new Myserverinitializer());

            ChannelFuture channelfuture = serverbootstrap.bind(8899).sync();
            channelfuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
