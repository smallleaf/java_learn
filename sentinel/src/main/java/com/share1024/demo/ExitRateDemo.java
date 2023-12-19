package com.share1024.demo;

import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.ExceptionCircuitBreaker;
import com.alibaba.csp.sentinel.slots.statistic.base.LeapArray;
import com.alibaba.csp.sentinel.slots.statistic.base.WindowWrap;

import java.util.List;

/**
 * @Description
 * @Date 2023年12月19日
 * @Created by yesheng
 */
public class ExitRateDemo {
    /**
     * 1秒10个窗口
     */
    private LeapArray<FlowRaiseCounter> stat = new LeapArray<FlowRaiseCounter>(5,1000) {

        @Override
        public FlowRaiseCounter newEmptyBucket(long timeMillis) {
            return new FlowRaiseCounter();
        }

        @Override
        protected WindowWrap<FlowRaiseCounter> resetWindowTo(WindowWrap<FlowRaiseCounter> windowWrap, long startTime) {
            windowWrap.resetTo(startTime);
            windowWrap.value().reset();
            return windowWrap;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ExitRateDemo exitRateDemo = new ExitRateDemo();
        for (int i = 0; i < 100; i++) {
            exitRateDemo.add(1,1);
            Thread.sleep(150);
        }
    }


    public void add(int enter,int exit) {
        FlowRaiseCounter flowRaiseCounter =  stat.currentWindow().value();
        flowRaiseCounter.getEnterCount().add(enter);
        flowRaiseCounter.getExitCount().add(exit);


        List<FlowRaiseCounter> counters = stat.values();
        long enterCount = 0;
        long exitCount = 0;
        StringBuilder enterSb = new StringBuilder();
        StringBuffer exitSb = new StringBuffer();
        for (FlowRaiseCounter counter : counters) {
            enterSb.append(counter.getEnterCount().sum()).append(",");
            exitSb.append(counter.getExitCount().sum()).append(",");
            enterCount += counter.getEnterCount().sum();
            exitCount += counter.getExitCount().sum();
        }
        System.out.println("enterStr:" + enterSb.toString() +",exit " + exitSb);
        System.out.println("enter " + enterCount + ", exit :" + exitCount);
    }

    public LeapArray<FlowRaiseCounter> getStat() {
        return stat;
    }

    public void setStat(LeapArray<FlowRaiseCounter> stat) {
        this.stat = stat;
    }
}
