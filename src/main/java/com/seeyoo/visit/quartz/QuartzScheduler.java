package com.seeyoo.visit.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ScheduledFuture;

/**
* class_name:
* param:
* describe: 定时任务类
* creat_user: ZhangGaoJun@zhanggj@seeyoo.cn
* creat_date: 2019/6/24
* creat_time: 13:17
**/
public class QuartzScheduler {
    private Logger logger = LoggerFactory.getLogger(QuartzScheduler.class);

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> scheduledFuture;

    private String cron = "";//事件表达式

    private Runnable runnable;//定时任务

    public QuartzScheduler(Runnable runnable, String cron, ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        super();
        this.runnable = runnable;
        this.cron = cron;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    public String getCron() {
        return cron;
    }

    /**
     * 停止定时任务
     */
    public void stop() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    /**
     * 设置时间表达式
     *
     * @param cron
     */
    public void setCron(String cron) {
        this.cron = cron;
        stop();
        scheduledFuture = threadPoolTaskScheduler.schedule(runnable, new QuartzTrigger(cron));
    }
}
