package com.example.study.demo.hystrix;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 熔断器实现类
 */
@Component
public class CircuitBreaker {

    private CircuitConfig circuitConfig;

    /**
     * 错误次数
     */
    private AtomicInteger failureCount = new AtomicInteger(0);

    /**
     * 恢复时间点
     */
    private volatile long recoverTime = Long.MAX_VALUE;

    /**
     * 已调用次数
     */
    private AtomicInteger hasAccessSampleSize = new AtomicInteger();

    /**
     * 当前熔断器状态 true:开启 false:关闭
     */
    private volatile boolean isOpen;

    public boolean getOpen() {
        return isOpen;
    }

    /**
     * 调用次数增加
     */

    public void increaseAccessCount() {
        hasAccessSampleSize.incrementAndGet();
    }

    /**
     * 重置失败次数
     */
    public void resetFailureCount() {
        failureCount.compareAndSet(failureCount.get(),0);
    }

    /**
     * 重置恢复时间
     */
    public void resetRecoverTime(){
        recoverTime = System.currentTimeMillis() + this.circuitConfig.getTimeout();
    }

    /**
     * 到达关闭时间
     */
    public boolean isTimeToClose(){
        return System.currentTimeMillis() >= recoverTime;
    }

    /**
     * 校验样本次数
     */
    private void checkAccessTime(){
        if (hasAccessSampleSize.intValue() <= this.circuitConfig.getSampleSize()){
            return;
        }
        hasAccessSampleSize = new AtomicInteger(0);
    }

    /**
     * 初始化熔断器，熔断器切换到闭合状态
     * @param circuitConfig
     */
    public CircuitBreaker(CircuitConfig circuitConfig)
    {
        this.circuitConfig = circuitConfig;

        if (this.circuitConfig.getFailureThreshold() < 1 || this.circuitConfig.getSampleSize() < 1) {
            throw new RuntimeException("熔断器闭合状态的最大失败次数和采样次数必须大于0");
        }
        if (this.circuitConfig.getTimeout() < 1) {
            throw new RuntimeException("熔断器断开状态超时时间必须大于0");
        }
        isOpen = isOpenState();
    }

    public void before() {
        increaseAccessCount();
        //如果是断开状态，直接返回
        if (isOpenState()) {
            return;
        }
        checkAccessTime();
    }

    /**
     * 方法调用发生异常操作后的操作
     */
    public void afterException() {
        //增加失败次数
        if (failureCount.incrementAndGet() == this.circuitConfig.getSampleSize()){
            resetRecoverTime();
        }
    }

    private boolean isOpenState() {
        //是否到达失败阀值
        if (failureCount.get() >= this.circuitConfig.getFailureThreshold()){
            isOpen = true;
        }

        //到达间隔时间
        if (isTimeToClose()){
            System.out.println("达到熔断时间关闭熔断器");
            isOpen = false;
            //重置失败次数
            resetFailureCount();
            //重置恢复时间
            recoverTime = Long.MAX_VALUE;
        }
        return isOpen;
    }
}