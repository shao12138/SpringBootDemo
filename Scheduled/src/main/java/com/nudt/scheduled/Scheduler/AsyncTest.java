package com.nudt.scheduled.Scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@EnableAsync // 开启异步事件的支持
@Component
public class AsyncTest {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTest.class);

//    @Async
//    @Scheduled(cron = "*/2 * * * * ?")
    public void taskCron() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        logger.info("SchedulerTask taskCron 现在时间： " + dateFormat.format(new Date()));
    }

//    @Async
//    @Scheduled(fixedRate = 1000)
    public void taskFixed() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        logger.info("SchedulerTask taskFixed 现在时间： " + dateFormat.format(new Date()));
    }
}