package com.example.study.demo.hystrix;

import java.util.concurrent.Semaphore;

public abstract class CircuitBreakerService {
    ThreadLocal<String> jj = ThreadLocal.withInitial(() -> "");

    private CircuitBreaker circuitBreaker;
    private final Semaphore semaphore;

    public CircuitBreakerService(CircuitBreaker circuitBreaker, Semaphore semaphore){
        this.circuitBreaker = circuitBreaker;
        this.semaphore = semaphore;
    }
    public Response execute(Request request) throws Exception {

        Response response = null;

        try {
            //获取共享资源
            semaphore.acquire();
            circuitBreaker.before();

            if (circuitBreaker.getOpen()) {
                return fallBack(request);
            }

            try {
                response = invoke(request);

            } catch (Exception e) {

                circuitBreaker.afterException();
                throw e;
            }

            if("9999".equals(response.getCode())){
                //非正常返回增加失败次数
                circuitBreaker.afterException();
            }
        }catch (InterruptedException e){

        }finally {
            //释放资源
            semaphore.release();
        }

        return response;
    }

    /**
     * 业务层覆盖发方法，为熔断时执行的业务方法
     */
    abstract protected Response invoke(Request request) throws Exception;

    /**
     * 业务层覆盖该方法，熔断后会执行该方法
     */
    abstract protected Response fallBack(Request request);

}
