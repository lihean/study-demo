package com.example.study.demo;

public class MyThread implements Runnable{

    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                System.out.println("------------log--------------");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("我死了~~~~~~~~~~~~~");
                e.printStackTrace();
            }
        }
    }

    public boolean getInterruptFlag(){
        return Thread.currentThread().isInterrupted();
    }

    public void setInterrupt(){
        Thread.currentThread().interrupt();
    }
}
