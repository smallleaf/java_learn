package com.share1024.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.getContext().put("skey","svalue");

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1","group1")
                    .usingJobData("t1","v1")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
            trigger.getJobDataMap().put("t2","v2");

            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .usingJobData("j1","v1")
                    .withIdentity("j2","v2").build();
            job.getJobDataMap().put("j2","v2");
            job.getJobDataMap().put("test","yesheng");
            scheduler.scheduleJob(job,trigger);
            scheduler.start();

            //scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
