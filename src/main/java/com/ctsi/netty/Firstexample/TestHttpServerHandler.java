package com.ctsi.netty.Firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
       if(msg instanceof HttpRequest){
           System.out.println(msg.getClass());
           System.out.println(ctx.channel().remoteAddress());
           HttpRequest httprequest=(HttpRequest)msg;
           System.out.println("请求方法名 "+httprequest.getMethod().name());
           URI uri=new URI(httprequest.uri());
           if("/favicon.ico".equals(uri.getPath())){
               System.out.println("请求favicon.ico");
               return;
           }

           System.out.println("fangwen channelRead0 ");

           ByteBuf content= Unpooled.copiedBuffer("hello sam", CharsetUtil.UTF_8);
           FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);

           response.headers().set(HttpHeaderNames.CONTENT_TYPE,"texct/plain");
           response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

           ctx.writeAndFlush(response);

           ctx.channel().close();
       }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }
}
