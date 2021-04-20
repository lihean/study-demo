package com.example.study.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        MyThread thread = new MyThread("A");
        Thread t = new Thread(thread);

        t.start();
        Thread.sleep(10000);
        t.interrupt();
        Thread.sleep(1000);
        System.out.println("结果："+t.isInterrupted());
    }
}
