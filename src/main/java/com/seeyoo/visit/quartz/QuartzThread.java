package com.seeyoo.visit.quartz;

import com.seeyoo.visit.bean.TgroupBean;
import com.seeyoo.visit.model.Assets;
import com.seeyoo.visit.service.AssetsService;
import com.seeyoo.visit.service.TgroupService;
import com.seeyoo.visit.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * class_name:
 * param:
 * describe: 定时任务线程
 * creat_user: ZhangGaoJun@zhanggj@seeyoo.cn
 * creat_date: 2019/6/24
 * creat_time: 13:16
 **/
public class QuartzThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        try {
            //由此定时任务线程，没在注入springbean，使用@Autowired获取不到bean，需要写一个获取bean的util来获取springbean
            //获取bean
    /*        IDemoService licenseCarrierService = SpringUtil.getBean(IDemoService.class);
            //执行任务
            lDemoService.method();*/
            AssetsService assetsService = SpringUtil.getBean(AssetsService.class);
            TgroupService tgroupService = SpringUtil.getBean(TgroupService.class);
            List<Assets> assets = assetsService.selectAll();
            logger.info("Ungrouped data>>>>>>>>>>>>>>>>>>" + assets.size());
            if (assets.size() > 0) {
                logger.info("同步分组开始............");
                List<Assets> assets1 = new ArrayList<Assets>();
                TgroupBean tgroupBean = null;
                for (Assets aset : assets) {
                    tgroupBean = tgroupService.codeByMac(aset.getMac());
                    if (tgroupBean != null) {
                        aset.setTgroupId(tgroupBean.getId());
                        aset.setTgroupCode(tgroupBean.getCode());
                        aset.setTerminalName(tgroupBean.getName());
                        assets1.add(aset);
                    }
                }
                if (assets1.size() > 0) {
                    assetsService.updateBatch(assets1);
                }
                logger.info(("同步成功===========>>>>>>" + assets1.size()));
            }
            logger.info("执行成功");
        } catch (Exception e) {
            logger.error("执行失败: " + e.getLocalizedMessage());
        }
    }
}
