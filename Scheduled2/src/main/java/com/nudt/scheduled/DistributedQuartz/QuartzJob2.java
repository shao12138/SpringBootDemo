package com.nudt.scheduled.DistributedQuartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

// 持久化
@PersistJobDataAfterExecution
// 禁止并发执行
@DisallowConcurrentExecution
public class QuartzJob2 extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(QuartzJob2.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String taskName = context.getJobDetail().getJobDataMap().getString("name");
        log.info("---> Quartz job2, time:{" + new Date() + "} ,name:{" + taskName + "}<----");
    }
}