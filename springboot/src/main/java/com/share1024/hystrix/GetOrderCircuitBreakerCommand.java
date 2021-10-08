package com.share1024.hystrix;

import com.netflix.hystrix.*;

import java.util.Random;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/10 09:41
 * \* Description:
 * \
 */
public class GetOrderCircuitBreakerCommand extends HystrixCommand<String> {

    private boolean openTest ;

    public GetOrderCircuitBreakerCommand(String name,boolean openTest){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
                        .andCommandKey(HystrixCommandKey.Factory.asKey(name))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
//                        .andCommandPropertiesDefaults(
//                                HystrixCommandProperties.Setter()
//                                        .withCircuitBreakerEnabled(true)//默认是true，本例中为了展现该参数
//                                        .withCircuitBreakerForceOpen(false)//默认是false，本例中为了展现该参数
//                                        .withCircuitBreakerForceClosed(false)//默认是false，本例中为了展现该参数
//                                        .withCircuitBreakerErrorThresholdPercentage(5)//(1)错误百分比超过5%
//                                        .withCircuitBreakerRequestVolumeThreshold(5)//(2)1以内调用次数10次，同时满足(1)(2)熔断器打开
//                                        .withCircuitBreakerSleepWindowInMilliseconds(5000)//隔5s之后，熔断器会尝试半开(关闭)，重新放进来请求
////                                .withExecutionTimeoutInMilliseconds(1000)
//                        )
//                        .andThreadPoolPropertiesDefaults(
//                                HystrixThreadPoolProperties.Setter()
//                                        .withMaxQueueSize(20)   //配置队列大小
//                                        .withCoreSize(20)    // 配置线程池里的线程数
//                        )
        );
        this.openTest = openTest;
    }

    @Override
    protected String run() throws Exception {
        Random rand = new Random();
        //模拟错误百分比(方式比较粗鲁但可以证明问题)
        if(openTest && 1==rand.nextInt(2)){
//            System.out.println("make exception");
            throw new Exception("make exception");
        }
        return "running:  ";
    }

    @Override
    protected String getFallback() {
//        System.out.println("FAILBACK");
        return "fallback: ";
    }


    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{

            for(int i=0;i<25000;i++){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HystrixCommand<String> command = new GetOrderCircuitBreakerCommand("testCircuitBreaker",true);
                String result = command.execute();
                //本例子中从第11次，熔断器开始打开
                System.out.println("call times:"+(i+1)+"   result:"+result +" isCircuitBreakerOpen: "+command.isCircuitBreakerOpen() +";;"+command.isSuccessfulExecution());
                //本例子中5s以后，熔断器尝试关闭，放开新的请求进来

//                HystrixCommand<String> command2 = new GetOrderCircuitBreakerCommand("testCircuitBreaker",false);
//                String result2 = command2.execute();
//                //本例子中从第11次，熔断器开始打开
//                System.out.println("2222call times:"+(i+1)+"   result:"+result2 +" isCircuitBreakerOpen: "+command2.isCircuitBreakerOpen());

            }
        }
        ).start();

//        new Thread(()->{
//
//            for(int i=0;i<25;i++){
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                HystrixCommand<String> command = new GetOrderCircuitBreakerCommand("testCircuitBreaker",false);
//                String result = command.execute();
//                //本例子中从第11次，熔断器开始打开
//                System.out.println("2222call times:"+(i+1)+"   result:"+result +" isCircuitBreakerOpen: "+command.isCircuitBreakerOpen());
//                //本例子中5s以后，熔断器尝试关闭，放开新的请求进来
//
//            }
//        }
//        ).start();

    }

}