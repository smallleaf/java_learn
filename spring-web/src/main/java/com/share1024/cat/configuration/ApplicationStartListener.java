package com.share1024.cat.configuration;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Date 2023年09月16日
 * @Created by yesheng
 */
@Component
public class ApplicationStartListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
      String hostIp =   event.getApplicationContext().getEnvironment().getProperty("server.ips");
      System.setProperty("server.ips",hostIp);
    }
}
