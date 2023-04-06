package com.nudt.scheduled.Scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTask {
    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    //@Scheduled(cron="*/1 * * * * ?")
    private void taskCron(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        logger.info("现在时间Scheduled1： " + dateFormat.format(new Date()));
    }

   //@Scheduled(fixedRate = 1000)
    public void taskFixed() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        logger.info("现在时间Scheduled2： " + dateFormat.format(new Date()));
    }
}