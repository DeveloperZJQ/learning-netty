package com.taohuasquare.netty.c3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author happy
 * @since 2022/2/2
 */
@Slf4j
public class TestJdkFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(() -> {
            log.debug("执行计算");
            Thread.sleep(3000);
            return 50;
        });

        log.debug("等待结果");
        log.debug("结果是 {}", future.get());
    }
}
