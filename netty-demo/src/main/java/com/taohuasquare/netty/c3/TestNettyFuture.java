package com.taohuasquare.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author happy
 * @since 2022/2/2
 */
@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop next = group.next();

        Future<Integer> future = next.submit(() -> {
            log.debug("执行计算");
            Thread.sleep(3000);
            return 70;
        });

//        log.debug("等待结果");
//        log.debug("结果是{}", future.get());
        future.addListener(future1 -> log.debug("获取计算结果:{}", future1.get()));

    }
}
