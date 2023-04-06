package com.nudt.scheduled.Quartz;

import com.nudt.scheduled.Scheduler.TestTask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SampleJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info(String.format("Hello %s!", this.name));
    }
}
