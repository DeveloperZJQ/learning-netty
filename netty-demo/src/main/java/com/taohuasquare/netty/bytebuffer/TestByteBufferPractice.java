package com.taohuasquare.netty.bytebuffer;

import java.nio.ByteBuffer;

import static com.taohuasquare.netty.bytebuffer.ByteBufferUtil.debugAll;

/**
 * @author happy
 * @since 2022/1/17
 */
public class TestByteBufferPractice {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        int limit = source.limit();
        for (int i = 0; i < limit; i++) {
            if (source.get(i) == '\n') {
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source); // 从source 读，向 target 写
                debugAll(target);
                source.limit(limit);
            }
        }
        source.compact();
    }
}
