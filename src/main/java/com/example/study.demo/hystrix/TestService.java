package com.example.study.demo.hystrix;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Semaphore;


@Service("testService")
public class TestService extends CircuitBreakerService {


    public TestService(CircuitBreaker circuitBreaker, Semaphore semaphore) {
        super(circuitBreaker, semaphore);
    }

    @Override
    protected Response invoke(Request request) throws Exception {

        //业务逻辑处理
        int result = new Random().nextInt(2);

        return result == 0?new Response("0000"):new Response("9999");
    }

    @Override
    protected Response fallBack(Request request) {
        return new Response("9999","系统繁忙，请稍后重试");
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(2));
    }
}
