package com.taohuasquare.chatline.chatClient;

import com.taohuasquare.chatline.entity.RpcRequest;
import io.netty.channel.Channel;

import java.util.UUID;

/**
 * @author happy
 * @since 2022-03-05
 */
public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        NettyClient client = new NettyClient("localhost", 8080);
        //启动client服务
        client.start();

        Channel channel = client.getChannel();
        //消息体
        RpcRequest request = new RpcRequest();
        request.setId("1111");
        request.setData("client message");
        //channel对象可保存在map中，供其他地方发送消息
        channel.writeAndFlush(request);
    }
}
