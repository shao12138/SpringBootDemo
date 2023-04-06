package com.nudt.scheduled.Quartz;

import org.quartz.*;

//@Configuration
public class SampleScheduler {
    //@Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
                .usingJobData("name", "燕双嘤")
                .storeDurably()
                .build();
    }

    //@Bean
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();
        return TriggerBuilder
                .newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("sampleTrigger")
                .withSchedule(scheduleBuilder).build();
    }
}