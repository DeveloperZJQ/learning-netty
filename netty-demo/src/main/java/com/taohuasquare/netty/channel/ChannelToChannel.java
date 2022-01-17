package com.taohuasquare.netty.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author happy
 * @since 2022/1/17
 */
public class ChannelToChannel {
    public static void main(String[] args) {
        try {
            //channel 转换 另一个channel
            FileChannel from = new FileInputStream("data.txt").getChannel();
            FileChannel to = new FileOutputStream("to.txt").getChannel();
            // 效率高，底层会利用操作系统的零拷贝进行优化
            // 传输字节大小限制为2G，超过就会传输不过去，怎么传输过去，多次循环传输
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
