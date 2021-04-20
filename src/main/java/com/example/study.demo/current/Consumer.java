package com.example.study.demo.current;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable{

    private AtomicInteger stock;

    public Consumer(AtomicInteger stock){
        this.stock = stock;
    }

    @Override
    public void run() {
        while (true){
            if(stock.intValue() > 5){
                stock.decrementAndGet();
                System.out.println("················消费者"+Thread.currentThread().getName()+"消费了一个库存···············");
            }else {
                System.out.println("·····························库存不足，暂不消费,stock:"+stock.intValue()+"·····························");
            }

        }
    }
}
