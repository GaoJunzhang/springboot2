package com.seeyoo.visit.scheduled;

import com.seeyoo.visit.bean.TgroupBean;
import com.seeyoo.visit.model.Assets;
import com.seeyoo.visit.service.AssetsService;
import com.seeyoo.visit.service.TgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Component
public class PorbeScheduled {

    @Autowired
    private AssetsService assetsService;

    @Autowired
    private TgroupService tgroupService;

    /**
     * class_name: PorbeScheduled
     * package: com.seeyoo.visit.scheduled
     * describe: 定时更新tgroupId
     * creat_user: ZhangGaoJun@zhanggj@seeyoo.cn
     * creat_date: 2019/6/5
     * creat_time: 16:10
     **/
    @Scheduled(cron = "0/60 * * * * *")
    @Transactional
    public void GetProbe() {
        Example example = new Example(Assets.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("tgroupCode");
        List<Assets> assets = assetsService.selectByExample(example);
        System.out.println("Ungrouped data>>>>>>>>>>>>>>>>>>" + assets.size());
        if (assets.size() > 0) {
            System.out.println("Start device grouping............");
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
            System.out.println("Update success===========" + assets1.size());
        }
    }
}
