package com.taohuasquare.chatline.chatServer;

import com.taohuasquare.chatline.entity.RpcRequest;
import com.taohuasquare.chatline.entity.RpcResponse;
import com.taohuasquare.chatline.message.RpcDecoder;
import com.taohuasquare.chatline.message.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {
    public void bind(int port) throws InterruptedException {
        //bossGroup就是parentGroup，是负责处理TCP/IP连接的
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup就是childGroup,是负责处理Channel(通道)的I/O事件
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                //初始化服务端可连接队列,指定了队列的大小128
                .option(ChannelOption.SO_BACKLOG, 128)
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //绑定客户端连续触发操作
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sh) throws Exception {
                        sh.pipeline()
                                //解码request
                                .addLast(new RpcDecoder(RpcRequest.class))
                                //编码response
                                .addLast(new RpcEncoder(RpcResponse.class))
                                //使用ServerHandler类来处理接收到的消息
                                .addLast(new ServerHandler());
                    }
                });
        //绑定监听端口 调用sync同步阻塞方法等待操作完
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        if (channelFuture.isSuccess()) {
            log.info("服务端启动成功");
        } else {
            log.info("服务端启动失败: {}", channelFuture.cause().getMessage());
            //关闭线程组
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

        //成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞, 直到channel关闭,线程才会往下执行,结束进程
        channelFuture.channel().closeFuture().sync();
    }
}
