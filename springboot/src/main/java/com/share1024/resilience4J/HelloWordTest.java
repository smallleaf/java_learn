package com.share1024.resilience4J;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2020/6/15 15:30
 * \* Description:
 * \
 */
public class HelloWordTest {

    @Test
    public void testCircuit(){
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .permittedNumberOfCallsInHalfOpenState(10)
                .slidingWindowSize(10)
                .waitDurationInOpenState(Duration.ofSeconds(3))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("backendName",circuitBreakerConfig);

        for (int i = 10; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Try.of(circuitBreaker.decorateCheckedSupplier(()->test(0))).isSuccess());
            System.out.println(circuitBreaker.getState());
        }


        for (int i = 10; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Try.of(circuitBreaker.decorateCheckedSupplier(()->test(1))).isSuccess());
            System.out.println(circuitBreaker.getState());
        }
        System.out.println(Try.of(circuitBreaker.decorateCheckedSupplier(()->test(0))).isSuccess());
        System.out.println(circuitBreaker.getState());
        System.out.println(Try.of(circuitBreaker.decorateCheckedSupplier(()->test(0))).isSuccess());
        System.out.println(circuitBreaker.getState());
        System.out.println(Try.of(circuitBreaker.decorateCheckedSupplier(()->test(0))).isSuccess());
        System.out.println(circuitBreaker.getState());
        System.out.println(Try.of(circuitBreaker.decorateCheckedSupplier(()->test(0))).isSuccess());
        System.out.println(circuitBreaker.getState());
        System.out.println(Try.of(circuitBreaker.decorateCheckedSupplier(()->test(2))).get());
        System.out.println(circuitBreaker.getState());
    }

    public String test(int i){
        int k= 10/i;
        return "test";
    }
}