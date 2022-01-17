package com.taohuasquare.netty.c1;

import java.nio.ByteBuffer;

/**
 * @author happy
 * @since 2022/1/17
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

        /**
         * class java.nio.HeapByteBuffer        --java堆内存 读写效率低
         * class java.nio.DirectByteBuffer      -- 直接内存，读写效率高（少一次拷贝），不影响GC效率
         */
    }
}
