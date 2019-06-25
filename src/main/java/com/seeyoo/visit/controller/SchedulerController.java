package com.seeyoo.visit.controller;

import com.seeyoo.visit.model.CronVo;
import com.seeyoo.visit.quartz.QuartzScheduler;
import com.seeyoo.visit.quartz.ScheduledFutureFactory;
import com.seeyoo.visit.result.ResultVO;
import com.seeyoo.visit.service.CronService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
    private Logger logger = LoggerFactory.getLogger(SchedulerController.class);

    @Autowired
    private CronService cronService;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ResultVO start(@RequestParam(name = "cronId", defaultValue = "", required = true) Integer cronId) {
        CronVo cronVo = cronService.selectByKey(cronId);
        if (cronVo == null) {
            return ResultVO.getFailed("cronId无效");
        }
        String cron = cronVo.getCron();
        String schedulerClass = cronVo.getSchedulerClass();
        //开启任务
        try {
            Runnable runnable = (Runnable) Class.forName(schedulerClass).newInstance();
            QuartzScheduler quartzScheduler = ScheduledFutureFactory.getQuartzScheduler(cronId);
            if (quartzScheduler == null) {
                quartzScheduler = ScheduledFutureFactory.createQuartzScheduler(cronId, runnable, cron, threadPoolTaskScheduler);
            }
            quartzScheduler.setCron(cron);
            cronVo.setStatus((short) 1);
            cronService.updateAll(cronVo);
            logger.info("开启定时任务成功");
            return ResultVO.getSuccess("开启定时任务成功", cronVo);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResultVO.getFailed("开启失败");
        }
    }

    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public ResultVO close(@RequestParam(name = "cronId", defaultValue = "", required = true) Integer cronId) {
        CronVo cronVo = cronService.selectByKey(cronId);
        if (cronVo == null) {
            return ResultVO.getFailed("cronId无效");
        }
        String cron = cronVo.getCron();
        String schedulerClass = cronVo.getSchedulerClass();
        //开启任务
        try {
            Runnable runnable = (Runnable) Class.forName(schedulerClass).newInstance();
            QuartzScheduler quartzScheduler = ScheduledFutureFactory.getQuartzScheduler(cronId);
            if (quartzScheduler == null) {
                quartzScheduler = ScheduledFutureFactory.createQuartzScheduler(cronId, runnable, cron, threadPoolTaskScheduler);
            }
            quartzScheduler.stop();
            cronVo.setStatus((short) 0);
            cronService.updateAll(cronVo);
            logger.info("关闭定时任务成功");
            return ResultVO.getSuccess("关闭定时任务成功", cronVo);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResultVO.getFailed("关闭失败");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVO update(@RequestParam(name = "cronId", required = true) Integer cronId, @RequestParam(name = "cron") String cron, @RequestParam(name = "status") short status, @RequestParam(name = "quartzName") String quartzName) {
        //参数校验
        if (StringUtils.isBlank(cron)) {
            return ResultVO.getFailed("时间表达式不可为空");
        }
        CronVo cronVo = cronService.selectByKey(cronId);
        if (cronVo == null) {
            return ResultVO.getFailed("任务不存在");
        }
        try {
            cronVo.setStatus(status);
            cronVo.setCron(cron);
            cronVo.setQuartzName(quartzName);
            // 2.更新实体，定时任务开启状态则重新设置表达式
            cronService.updateAll(cronVo);
            if (status == 1) {
                Runnable runnable = (Runnable) Class.forName(cronVo.getSchedulerClass()).newInstance();
                QuartzScheduler quartzScheduler = ScheduledFutureFactory.getQuartzScheduler(cronId);
                if (quartzScheduler == null) {
                    quartzScheduler = ScheduledFutureFactory.createQuartzScheduler(cronId, runnable, cron,
                            threadPoolTaskScheduler);
                }
                quartzScheduler.setCron(cron);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultVO.getFailed("更新定时任务失败");
        }
        logger.info("更新定时任务成功");
        return ResultVO.getFailed("更新定时任务成功");
    }

    /***
     * 根据主键获取定时任务相关信息
     *
     * @param cronId
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public CronVo findById(@RequestParam(name = "cronId", defaultValue = "") Integer cronId) {
        if (cronId == null) {
            return null;
        }
        CronVo cronVO = cronService.selectByKey(cronId);
        return cronVO;
    }

    /***
     * 获取所有定时任务信息
     *
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAll() {
        List<CronVo> list = cronService.selectAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", list.size());
        return map;
    }
}
