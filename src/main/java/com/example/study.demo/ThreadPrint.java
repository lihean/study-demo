package com.example.study.demo;

public class ThreadPrint {

    private static Object object = new Object();
    private static volatile int number = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true){
                    synchronized (object){

                        if (number % 2 != 0){

                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        System.out.println(Thread.currentThread().getName() + number);
                        number++;

                        object.notify();
                    }

                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true){
                    synchronized (object){

                        if (number % 2 == 0){
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        System.out.println(Thread.currentThread().getName()+ number);
                        number++;
                        object.notify();
                    }

                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        t2.start();
        t2.setName("奇数线程---");

        t1.start();
        t1.setName("偶数线程---");

    }
}
