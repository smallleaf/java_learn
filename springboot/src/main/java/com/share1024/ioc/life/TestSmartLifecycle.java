package com.share1024.ioc.life;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/26
 */
@Component
public class TestSmartLifecycle implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("====TestSmartLifecycle=====start=========");
    }

    @Override
    public void stop() {
        System.out.println("=======TestSmartLifecycle========stop=======");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
