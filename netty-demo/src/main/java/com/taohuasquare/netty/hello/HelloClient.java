package com.taohuasquare.netty.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author happy
 * @since 2022/1/19
 */
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        // 1. 启动类
        new Bootstrap()
                // 2. 添加EventLoop
                .group(new NioEventLoopGroup())
                // 3. 选择 客户端 channel的实现
                .channel(NioSocketChannel.class)
                // 4. 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override   // 在建立连接后被调用
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 5. 连接到服务器
                .connect(new InetSocketAddress("localhost", 8080))
                .sync()
                .channel()
                // 6. 向服务器发送数据
                .writeAndFlush("hello, world");
    }
}
