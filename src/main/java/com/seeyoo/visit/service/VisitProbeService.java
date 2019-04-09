package com.seeyoo.visit.service;

import com.github.pagehelper.PageInfo;
import com.seeyoo.visit.bean.AssetsBean;
import com.seeyoo.visit.bean.DayVisitBean;
import com.seeyoo.visit.bean.VisitStatisBean;
import com.seeyoo.visit.bean.VisitTimeBean;
import com.seeyoo.visit.model.VisitProbe;

import java.sql.Timestamp;
import java.util.List;

public interface VisitProbeService extends IService<VisitProbe> {
    public VisitProbe selectByMacAndTime(String mac, Timestamp beginTime);

    public PageInfo<VisitStatisBean> selectByPage(Timestamp start, Timestamp end, Integer assetsId, int page, int size);

    public List<VisitTimeBean> findDistinctByMacAndTime(String time, Integer assetsId);

    public List<DayVisitBean> dayVisits(String time, Integer assetsId);


    public List<VisitStatisBean> dayVisitCount(Timestamp start, Timestamp end);

    public List<VisitStatisBean> dayVisitVaildCount(Timestamp start, Timestamp end, int sdb, int edb);

    public int getAllVisitTime(Timestamp start, Timestamp end);

    public List<DayVisitBean> dayVisiters(Timestamp start, Timestamp end);

    public List<AssetsBean> top10Assets(Timestamp start, Timestamp end);

    public List<AssetsBean> top10VaildAssets(Timestamp start, Timestamp end, int sdb, int edb);
}
