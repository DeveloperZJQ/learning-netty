package com.taohuasquare.chatline.chatClient;

import com.taohuasquare.chatline.entity.RpcRequest;
import com.taohuasquare.chatline.entity.RpcResponse;
import com.taohuasquare.chatline.message.RpcDecoder;
import com.taohuasquare.chatline.message.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {
    private final String host;
    private final int port;
    private Channel channel;

    //连接服务端的端口号地址和端口号
    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(clientGroup)
                //使用NioSocketChannel来作为连接用的channel类
                .channel(NioSocketChannel.class)
                //绑定连接初始化器
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        System.out.println("正在连接中...");
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //编码request
                        pipeline.addLast(new RpcEncoder(RpcRequest.class));
                        //解码response
                        pipeline.addLast(new RpcDecoder(RpcResponse.class));
                        //客户端处理类
                        pipeline.addLast(new ClientHandler());

                    }
                });
        final ChannelFuture future = bootstrap.connect(host, port).sync();
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("连接服务器成功");
            } else {
                System.err.println("连接服务器失败:" + future.cause().getMessage());
                clientGroup.shutdownGracefully();
            }
        });

        this.channel = future.channel();
    }

    public Channel getChannel() {
        return channel;
    }
}

