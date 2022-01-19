package com.taohuasquare.netty.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author happy
 * @since 2022/1/19
 */
public class HelloServer {
    public static void main(String[] args) {

        //1. 启动器 负责组装netty组件，启动服务器
        new ServerBootstrap()
                //2. BossEventLoop ,WorkerEventLoop(包含selector,thread), group组
                .group(new NioEventLoopGroup())
                //3. 选择 服务器的 ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                //4. boss负责处理连接 worker(child) 负责处理读写,决定了worker(child) 能执行哪些操作(handler)
                .childHandler(
                        //5. channel 代表和客户端进行数据读写的通道Initializer初始化,负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nioSocketChannel) {
                                //6. 添加具体handler
                                // 将ByteBuf转换为字符串
                                nioSocketChannel.pipeline().addLast(new StringDecoder());
                                // 自定义handler
                                nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override//读事件
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                        // 打印上一步转换好的字符串
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                // 7. 绑定监听端口
                .bind(8080);
    }
}
