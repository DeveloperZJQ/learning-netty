package com.taohuasquare.netty.c3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.taohuasquare.netty.c1.bytebuffer.ByteBufferUtil.debugAll;

/**
 * @author happy
 * @since 2022/1/17
 */
@Slf4j
public class SocketServer {
    public static void main(String[] args) throws IOException {
        //使用nio 来理解阻塞模式,单线程
        // 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //1. 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //该配置项是 非阻塞模式
//        ssc.configureBlocking(false);
        //2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
        //3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            //4. accept 建立与客户端连接，SocketChannel 用户与客户端之间通信
            log.debug("connecting...");
            // accept() 默认是阻塞方法，线程停止运行；客户端建立以后，继续进行
            SocketChannel sc = ssc.accept();
            // read 非阻塞模式
//            sc.configureBlocking(false);
            log.debug("connected... {}", sc);
            channels.add(sc);
            for (SocketChannel channel : channels) {
                //5. 接收客户端发送的数据
                log.debug("before read... {}", channel);
                // read() 默认是阻塞方法，线程停止运行；
                channel.read(buffer);
                buffer.flip();
                debugAll(buffer);
                buffer.clear();
                log.debug("after read... {}", channel);
            }
        }

    }
}
