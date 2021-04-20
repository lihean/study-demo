package com.example.study.demo.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ConcurrentQueueDemo {
    static LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(1 << 10);
    public static void main(String[] args) {
        testDeque();

    }

    public static void testDeque() {
        log.info("开始生产数据...");
        log.info("开始生产数据...");
        for (int i = 0; i < 100; i++) {
            deque.addFirst("Lihean_" + i);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("开始消费数据...");
        while (!deque.isEmpty()) {
            try {
                System.out.println(deque.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Integer.MAX_VALUE);


        AtomicInteger atomicInteger = new AtomicInteger(16);
        atomicInteger.incrementAndGet();
        log.info(String.valueOf(atomicInteger.intValue()));
        log.info(String.valueOf(atomicInteger.get()));
        log.info(String.valueOf(atomicInteger.doubleValue()));
    }
}
