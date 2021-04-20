package com.example.study.demo.current;

import org.omg.PortableServer.ThreadPolicy;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class MsgQueue {

    private static ArrayBlockingQueue<String> msgQueue = new ArrayBlockingQueue<>(1 << 20);
    static ExecutorService poolExecutor = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());

    public static class provider implements Runnable {

        @Override
        public void run() {
            try {
                msgQueue.put(UUID.randomUUID().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static class consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class CallConsumer implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return UUID.randomUUID().toString();
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new consumer());
        thread1.start();

        Thread thread2 = new Thread(new consumer());
        thread2.start();
//        thread1.interrupt();
//        System.out.print(thread1.isInterrupted());
        LockSupport.park(thread1);
        printThreadState(thread1);

//        Future<String> future = poolExecutor.submit(new CallConsumer());
//        try {
//            System.out.print(future.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public static void printThreadState(Thread thread) {
        System.out.println(thread.getState());
    }
}
