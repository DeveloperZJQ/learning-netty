package com.taohuasquare.netty.component;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author happy
 * @since 2022/1/23
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        //创建事件循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(2);

        //获取下一个事件循环对象
        System.out.println(eventExecutors.next());
        System.out.println(eventExecutors.next());

        // 执行普通任务
        eventExecutors.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });

        // 执行定时任务
        eventExecutors.next().scheduleAtFixedRate(() -> {
            log.debug("ok");
        }, 0, 1, TimeUnit.SECONDS);
        log.debug("main");

        System.out.println("本机可利用核数：" + getLocalCore());
    }

    private static int getLocalCore() {
        return NettyRuntime.availableProcessors();
    }
}
