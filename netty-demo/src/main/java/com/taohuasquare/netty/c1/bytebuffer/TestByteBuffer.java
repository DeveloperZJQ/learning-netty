package com.taohuasquare.netty.c1.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer正确使用姿势
 * 1. 向buffer写入数据，例如调用channel.read(buffer)
 * 2. 调用flip()切换至读模式
 * 3. 从buffer读取数据，例如调用buffer.get()
 * 4. 调用clear()或compact切换至写模式
 * 5. 重复1-4步骤
 *
 * @author happy
 * @since 2022/1/16
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        //FileChannel
        //1. 输入输出流，2. RandomAccessFile
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer allocate = ByteBuffer.allocate(10);

            while (true) {
                int len = channel.read(allocate);
                log.debug("read byte num {}", len);

                if (len == -1) {
                    break;
                }
                //打印buffer 的内容

                //切换成读的模式
                allocate.flip();

                // 判断是否剩余未读数据
                while (allocate.hasRemaining()) {
                    byte b = allocate.get();
                    log.debug("read byte num {}", (char) b);
                }
                allocate.clear();
            }
        } catch (IOException e) {
            log.error("error:{}", e.getMessage());
        }
    }
}
