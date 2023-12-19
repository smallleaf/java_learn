package com.share1024.demo;

import com.alibaba.csp.sentinel.slots.statistic.base.LongAdder;

/**
 * @Description
 * @Date 2023年12月19日
 * @Created by yesheng
 */
public class FlowRaiseCounter {
    private LongAdder enterCount;
    private LongAdder exitCount;

    public FlowRaiseCounter() {
        this.enterCount = new LongAdder();
        this.exitCount = new LongAdder();
    }

    public LongAdder getEnterCount() {
        return enterCount;
    }

    public void setEnterCount(LongAdder enterCount) {
        this.enterCount = enterCount;
    }

    public LongAdder getExitCount() {
        return exitCount;
    }

    public void setExitCount(LongAdder exitCount) {
        this.exitCount = exitCount;
    }

    public FlowRaiseCounter reset() {
        exitCount.reset();
        enterCount.reset();
        return this;
    }

}
