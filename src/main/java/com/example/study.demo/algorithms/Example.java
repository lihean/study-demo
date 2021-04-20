package com.example.study.demo.algorithms;

public class Example extends Thread{
    @Override

    public void run(){

        try {

            Thread.sleep(1000);

        } catch (InterruptedException e){

            e.printStackTrace();

        }

        System. out .print( "run" );

    }

    public static void main(String[] args){

        Example example= new Example();

        example.run();

        System. out .print( "main" );

    }
}
