package com.example.study.demo.current;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {


    public static void main(String[] args) {
        AtomicInteger stock = new AtomicInteger(10);

        Thread t1 = new Thread(new Consumer(stock));
        Thread t2 = new Thread(new Consumer(stock));
        Thread t3 = new Thread(new Consumer(stock));

        Thread t4 = new Thread(new Provider(stock));
        Thread t5 = new Thread(new Provider(stock));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();




    }
}
