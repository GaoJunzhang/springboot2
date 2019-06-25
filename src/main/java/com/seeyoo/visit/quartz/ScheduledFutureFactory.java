package com.seeyoo.visit.quartz;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.HashMap;
import java.util.Map;

/**
* class_name: ScheduledFutureFactory
* param:
* describe: 定时任务工厂
* creat_user: ZhangGaoJun@zhanggj@seeyoo.cn
* creat_date: 2019/6/24
* creat_time: 13:18
**/
public class ScheduledFutureFactory {
    private static Map<Integer, QuartzScheduler> map = new HashMap<>(0);

    /**
    * class_name: ScheduledFutureFactory
    * package: com.seeyoo.visit.quartz
    * describe: 获取定时任务实例
    * creat_user: ZhangGaoJun@zhanggj@seeyoo.cn
    * creat_date: 2019/6/24
    * creat_time: 13:31
    **/
    public static QuartzScheduler createQuartzScheduler(Integer cronId, Runnable runnable, String cron,
                                                        ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        QuartzScheduler quartzScheduler = new QuartzScheduler(runnable, cron, threadPoolTaskScheduler);
        map.put(cronId, quartzScheduler);
        return quartzScheduler;
    }

    /**
    * class_name: ScheduledFutureFactory
    * package: com.seeyoo.visit.quartz
    * describe: 根据key获取定时任务实例
    * creat_user: ZhangGaoJun@zhanggj@seeyoo.cn
    * creat_date: 2019/6/24
    * creat_time: 13:31
    **/
    public static QuartzScheduler getQuartzScheduler(Integer cronId) {
        return map.get(cronId);
    }
}
