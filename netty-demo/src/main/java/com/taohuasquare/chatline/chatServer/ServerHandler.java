package com.taohuasquare.chatline.chatServer;

import com.taohuasquare.chatline.entity.RpcRequest;
import com.taohuasquare.chatline.entity.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author happy
 * @since 2022-03-05
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    //接收client发送消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        RpcRequest request = (RpcRequest) msg;
        log.info("接收到客户端信息：" + request.toString());
        //返回的数据结构
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(UUID.randomUUID().toString());
        rpcResponse.setData("server响应结果");
        rpcResponse.setStatus(1);
        ctx.writeAndFlush(rpcResponse);
    }

    //通知处理器最后的channelRead 是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.info("服务端接收数据完毕..");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("读操作时捕获到异常:{}", cause.getMessage());
        ctx.close();
    }

    //客户端去和服务端连接成功触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        super.channelActive(ctx);
    }
}
