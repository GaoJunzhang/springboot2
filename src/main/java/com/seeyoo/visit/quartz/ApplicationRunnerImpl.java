package com.seeyoo.visit.quartz;

import com.seeyoo.visit.model.Assets;
import com.seeyoo.visit.model.CronVo;
import com.seeyoo.visit.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private CronService cronService;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("启动后自动开启已配置定时任务");
        Example example = new Example(CronVo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        List<CronVo> list = cronService.selectByExample(example);
        System.out.println(list.size());
        if (list.size() > 0) {
            for (CronVo cronVo:list){
                Runnable runnable = (Runnable) Class.forName(cronVo.getSchedulerClass()).newInstance();
                QuartzScheduler quartzScheduler = ScheduledFutureFactory.getQuartzScheduler(cronVo.getCronId());
                if (quartzScheduler == null) {
                    quartzScheduler = ScheduledFutureFactory.createQuartzScheduler(cronVo.getCronId(), runnable, cronVo.getCron(),
                            threadPoolTaskScheduler);
                }
                quartzScheduler.setCron(cronVo.getCron());
            }
        }
        System.out.println("定时任务初始化成功");
    }
}
