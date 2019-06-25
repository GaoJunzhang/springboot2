package com.seeyoo.visit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seeyoo.visit.bean.AssetsBean;
import com.seeyoo.visit.bean.DayVisitBean;
import com.seeyoo.visit.bean.VisitStatisBean;
import com.seeyoo.visit.bean.VisitTimeBean;
import com.seeyoo.visit.mapper.VisitProbeMapper;
import com.seeyoo.visit.model.VisitProbe;
import com.seeyoo.visit.service.VisitProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("visitProbeService")
public class VisitProbeServiceImpl extends BaseService<VisitProbe> implements VisitProbeService {
    @Autowired
    private VisitProbeMapper visitProbeMapper;

    public VisitProbe selectByMacAndTimeAndAssetsId(String mac, Timestamp beginTime, Integer assetsId) {
        return visitProbeMapper.selectByMacAndTimeAndAssetsId(mac, beginTime, assetsId);
    }

    public PageInfo<VisitStatisBean> selectByPage(Timestamp start, Timestamp end, Integer assetsId, int page, int size) {
        PageHelper.startPage(page, size);
        List<VisitStatisBean> visitProbes = visitProbeMapper.DailysByPage(start, end, assetsId);
        return new PageInfo<VisitStatisBean>(visitProbes);
    }

    public List<VisitTimeBean> findDistinctByMacAndTime(String time, Integer assetsId) {
        return visitProbeMapper.findDistinctByMacAndTime(time, assetsId);
    }

    public List<DayVisitBean> dayVisits(String time, Integer assetsId) {
        return visitProbeMapper.dayVisits(time, assetsId);
    }

    public List<VisitStatisBean> dayVisitCount(Timestamp start, Timestamp end, int[] idList,String code) {
        return visitProbeMapper.dayVisitCount(start, end, idList,code);
    }

    public List<VisitStatisBean> dayVisitVaildCount(Timestamp start, Timestamp end, int sdb, int edb, int[] idList,String code) {
        return visitProbeMapper.dayVisitVaildCount(start, end, sdb, edb, idList,code);
    }

    public long getAllVisitTime(Timestamp start, Timestamp end, int[] idList,String code) {
        return visitProbeMapper.getAllVisitTime(start, end,idList,code);
    }

    public List<DayVisitBean> dayVisiters(Timestamp start, Timestamp end) {
        return visitProbeMapper.dayVisiters(start, end);
    }

    public List<AssetsBean> top10Assets(Timestamp start, Timestamp end, int[] idList,String code) {
        return visitProbeMapper.top10Assets(start, end, idList,code);
    }

    public List<AssetsBean> top10VaildAssets(Timestamp start, Timestamp end, int sdb, int edb, int[] idList,String code) {
        return visitProbeMapper.top10VaildAssets(start, end, sdb, edb, idList,code);
    }

    public int countOldByTime(Timestamp start, Timestamp end, int[] idList,String code) {
        return visitProbeMapper.countOldByTime(start, end,idList,code);
    }

    public int dayOldVisits(String time, Integer assetsId) {
        return visitProbeMapper.dayOldVisits(time, assetsId);
    }
}
