package com.ctsi.netty.Firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


public class TestServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipline =ch.pipeline();
        pipline.addLast("httpservercodec",new HttpServerCodec());
        pipline.addLast("testhttpserverhandler",new TestHttpServerHandler());


    }
}
