package com.taohuasquare.chatline.chatClient;

import com.taohuasquare.chatline.entity.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author happy
 * @since 2022-3-5
 */
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    //处理服务端返回的数据
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) {
        System.out.println("接受到server响应数据: " + rpcResponse.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.err.println("ClientHandler捕获错误:" + cause.getMessage());
    }
}
