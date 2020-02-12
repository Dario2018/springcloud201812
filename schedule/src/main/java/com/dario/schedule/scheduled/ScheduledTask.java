package com.dario.schedule.scheduled;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class ScheduledTask {
//    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    /**
     * 每隔5秒执行, 单位：ms。
     */
    @Scheduled(fixedRate = 5000)
    public void testFixRate() {
        System.out.println("我每隔5秒冒泡一次：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 1 * * ?")    //每天凌晨1点执行
    public void testMyBatis() {
        System.out.println("我每天凌晨1点开始执行");

    }

    /**
     * 在每个小时 30，32，38分执行一次
     * */
    @Scheduled(cron = "0 35,38,39 * * * ?")
    public void testSendMessages(){
        System.out.println("在每个小时的第35，38，39分执行一次"+dateFormat.format(new Date()));
    }
}

