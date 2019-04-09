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

    public VisitProbe selectByMacAndTime(String mac, Timestamp beginTime) {
        return visitProbeMapper.selectByMacAndTime(mac, beginTime);
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

    public List<VisitStatisBean> dayVisitCount(Timestamp start, Timestamp end) {
        return visitProbeMapper.dayVisitCount(start, end);
    }

    public List<VisitStatisBean> dayVisitVaildCount(Timestamp start, Timestamp end, int sdb, int edb) {
        return visitProbeMapper.dayVisitVaildCount(start, end, sdb, edb);
    }

    public int getAllVisitTime(Timestamp start, Timestamp end) {
        return visitProbeMapper.getAllVisitTime(start, end);
    }

    public List<DayVisitBean> dayVisiters(Timestamp start, Timestamp end) {
        return visitProbeMapper.dayVisiters(start, end);
    }

    public List<AssetsBean> top10Assets(Timestamp start, Timestamp end){
        return visitProbeMapper.top10Assets(start,end);
    }

    public List<AssetsBean> top10VaildAssets(Timestamp start, Timestamp end, int sdb, int edb){
        return visitProbeMapper.top10VaildAssets(start,end,sdb,edb);
    }
}