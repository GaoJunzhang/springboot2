package com.seeyoo.visit.quartz;


import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * 定时任务trigger
 *
 * @author Administrator
 *
 */
public class QuartzTrigger implements Trigger {

    private String cron;

    public QuartzTrigger(String cron) {
        super();
        this.cron = cron;
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        if (StringUtils.isBlank(cron)) {
            return null;
        }
        // 定时任务触发，可修改定时任务的执行周期
        CronTrigger trigger = new CronTrigger(cron);
        Date nextExecDate = trigger.nextExecutionTime(triggerContext);
        return nextExecDate;
    }

}
