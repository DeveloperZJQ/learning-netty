package com.taohuasquare.netty.c1;

import java.nio.ByteBuffer;

import static com.taohuasquare.netty.c1.ByteBufferUtil.debugAll;

/**
 * @author happy
 * @since 2022/1/17
 */
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        debugAll(buffer);

        buffer.flip();
        // 超过buffer size 会报 indexOutOfBoundsException
        buffer.get(1);
        debugAll(buffer);
    }
}
