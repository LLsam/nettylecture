package com.ctsi.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelgroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //任何客户端发送消息 都会收到
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        channelgroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + "发送的" + s);
            }else{
                ch.writeAndFlush("自己发送");
            }
        }


        );
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelgroup.writeAndFlush("[服务器]=" + channel.remoteAddress() + "上线\n");
        channelgroup.add(channel);
        // super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //channelgroup.remove(channel);会自动调用
        channelgroup.writeAndFlush("[服务器]=" + channel.remoteAddress() + "离线\n");
        //super.handlerRemoved(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
        // super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
        // super.channelInactive(ctx);
    }
}
