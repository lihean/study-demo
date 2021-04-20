package com.example.study.demo.current;

import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable{

    private AtomicInteger stock;

    public Provider(AtomicInteger stock){
        this.stock = stock;
    }

    @Override
    public void run() {
        while (true){
            if(stock.intValue() < 20){
                stock.incrementAndGet();
                System.out.println("················消费者"+Thread.currentThread().getName()+"生产了一个库存···············");
            }else {
                System.out.println("·····························库存足够，暂不生产,stock:"+stock.intValue()+"·····························");
            }
        }
    }
}
