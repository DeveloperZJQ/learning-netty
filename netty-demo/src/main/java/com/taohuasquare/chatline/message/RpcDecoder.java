package com.taohuasquare.chatline.message;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author happy
 * @since 2022-3-5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class RpcDecoder extends ByteToMessageDecoder {

    //目标对象类型进行解码
    private Class<?> target;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        //不够长度丢弃
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        //标记一下当前的readIndex的位置
        byteBuf.markReaderIndex();
        int dataLength = byteBuf.readInt(); // 读取传送过来的消息的长度。ByteBuf 的readInt()方法会让他的readIndex增加4

        if (byteBuf.readableBytes() < dataLength) { //读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex. 这个配合markReaderIndex使用的。把readIndex重置到mark的地方
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);

        Object obj = JSON.parseObject(data, target); //将byte数据转化为我们需要的对象
        list.add(obj);
    }
}
