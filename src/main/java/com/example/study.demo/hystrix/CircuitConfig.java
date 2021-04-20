package com.example.study.demo.hystrix;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class CircuitConfig {

    /**
     * 样本次数
     */
    @Value(value = "circuit.sampleSize")
    private int sampleSize;

    /**
     * 错误次数阀值
     */
    @Value(value = "circuit.failureThreshold")
    private int failureThreshold;

    /**
     * 恢复时间间隔
     */
    @Value(value = "circuit.timeout")
    private long timeout;
}
