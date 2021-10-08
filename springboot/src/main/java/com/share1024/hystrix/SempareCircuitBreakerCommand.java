package com.share1024.hystrix;

import com.netflix.hystrix.*;

import java.util.Random;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/10 09:41
 * \* Description:
 * \
 */
public class SempareCircuitBreakerCommand extends HystrixCommand<String> {

    private boolean openTest ;

    public SempareCircuitBreakerCommand(String name, boolean openTest){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
                        .andCommandKey(HystrixCommandKey.Factory.asKey(name))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerEnabled(true)//默认是true，本例中为了展现该参数
                                        .withCircuitBreakerForceOpen(false)//默认是false，本例中为了展现该参数
                                        .withCircuitBreakerForceClosed(false)//默认是false，本例中为了展现该参数
//                                        .withCircuitBreakerErrorThresholdPercentage(5)//(1)错误百分比超过5%
//                                        .withCircuitBreakerRequestVolumeThreshold(1)//(2)1以内调用次数10次，同时满足(1)(2)熔断器打开
                                        .withCircuitBreakerSleepWindowInMilliseconds(5000000)//隔5s之后，熔断器会尝试半开(关闭)
                                        // ，重新放进来请求
                                .withExecutionTimeoutInMilliseconds(10000000)
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
                        )
        );
        this.openTest = openTest;
    }

    @Override
    protected String run() throws Exception {
        Random rand = new Random();
        //模拟错误百分比(方式比较粗鲁但可以证明问题)
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
        }
        return "running:  ";
    }

    @Override
    protected String getFallback() {
//        System.out.println("FAILBACK");
        return "fallback: ";
    }


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            if(i <= 5){
                new Thread(()->{
                    HystrixCommand<String> command = new SempareCircuitBreakerCommand("testCircuitBreaker",true);
                    String result = command.execute();
                    //本例子中从第11次，熔断器开始打开
                    System.out.println("call times:"+(finalI +1)+"   result:"+result +" isCircuitBreakerOpen: "+command.isCircuitBreakerOpen() +";;"+command.isSuccessfulExecution());
                    //本例子中5s以后，熔断器尝试关闭，放开新的请求进来
                }
                ).start();
            }else{
                new Thread(()->{
                    HystrixCommand<String> command = new SempareCircuitBreakerCommand("testCircuitBreaker2",true);
                    String result = command.execute();
                    //本例子中从第11次，熔断器开始打开
                    System.out.println("call222 times:"+(finalI +1)+"   result:"+result +" isCircuitBreakerOpen: "+command.isCircuitBreakerOpen() +";;"+command.isSuccessfulExecution());
                    //本例子中5s以后，熔断器尝试关闭，放开新的请求进来
                }
                ).start();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        }


//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2 ,1000 ,TimeUnit.HOURS ,
//                new ArrayBlockingQueue<>(2));
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//            threadPoolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        TimeUnit.SECONDS.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}