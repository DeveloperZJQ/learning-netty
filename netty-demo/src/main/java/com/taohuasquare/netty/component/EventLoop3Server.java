package com.taohuasquare.netty.component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * 再次细分
 *
 * @author zhangjianqiang
 * @since 2022-01-24
 */
@Slf4j
public class EventLoop3Server {
    public static void main(String[] args) {
        DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        new ServerBootstrap()
                .group(defaultEventLoopGroup, nioEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                log.debug(byteBuf.toString(Charset.defaultCharset()));
                                ctx.fireChannelRead(msg);
                            }
                        }).addLast(defaultEventLoopGroup, "handler2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                log.debug(byteBuf.toString(Charset.defaultCharset()));
                            }
                        });
                    }
                })
                .bind(8080);
    }
}
