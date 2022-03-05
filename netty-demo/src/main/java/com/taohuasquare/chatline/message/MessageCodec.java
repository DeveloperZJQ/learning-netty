package com.taohuasquare.chatline.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * 自定义协议要素
 * 魔数：用来在第一时间判断是否是无效数据包
 * 版本号：可以支持协议升级
 * 序列化算法：json，protobuf，jdk，hessian
 * 指令类型：登录、注册。。。
 * 请求序号：为了双工通信，提供异步能力
 * 正文长度
 * 正文消息
 *
 * @author happy
 * @since 2022-02-08
 */
public class MessageCodec extends ByteToMessageCodec<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
