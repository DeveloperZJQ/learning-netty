package com.taohuasquare.netty.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;

/**
 * @author happy
 * @since 2022/2/6
 */
public class RedisProtocol {
    public static void main(String[] args) throws InterruptedException {
        final byte[] LINE = {13, 10};
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(worker)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new LoggingHandler());
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                ByteBuf buf = ctx.alloc().buffer();
                                buf.writeBytes("*3".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$3".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("set".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$4".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("name".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("$8".getBytes());
                                buf.writeBytes(LINE);
                                buf.writeBytes("zhangsan".getBytes());
                                buf.writeBytes(LINE);
                                ctx.writeAndFlush(buf);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf buf = (ByteBuf) msg;
                                System.out.println(buf.toString(Charset.defaultCharset()));
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("47.103.9.204", 6379).sync();
        channelFuture.channel().closeFuture().sync();
    }
}
