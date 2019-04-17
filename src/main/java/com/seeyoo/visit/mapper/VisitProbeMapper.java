package com.seeyoo.visit.mapper;

import com.seeyoo.visit.bean.AssetsBean;
import com.seeyoo.visit.bean.DayVisitBean;
import com.seeyoo.visit.bean.VisitStatisBean;
import com.seeyoo.visit.bean.VisitTimeBean;
import com.seeyoo.visit.model.VisitProbe;
import com.seeyoo.visit.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface VisitProbeMapper extends MyMapper<VisitProbe> {
    public VisitProbe selectByMacAndTimeAndAssetsId(@Param("mac") String mac, @Param("beginTime")Timestamp beginTime, @Param("assetsId") Integer assetsId);

    public List<VisitStatisBean> DailysByPage(@Param("start") Timestamp start, @Param("end") Timestamp end, @Param("assetsId") Integer assetsId);

    public List<VisitTimeBean> findDistinctByMacAndTime(@Param("time") String time, @Param("assetsId") Integer assetsId);

    public List<DayVisitBean> dayVisits(@Param("time") String time, @Param("assetsId") Integer assetsId);

    public List<VisitStatisBean> dayVisitCount(@Param("start") Timestamp start, @Param("end") Timestamp end);

//    public List<DayVisitBean> dayVisits(String time, int assetsId);

    public List<VisitStatisBean> dayVisitVaildCount(@Param("start") Timestamp start, @Param("end") Timestamp end,@Param("sdb") int sdb,@Param("edb") int edb);

    public int getAllVisitTime(@Param("start") Timestamp start, @Param("end") Timestamp end);

    public List<DayVisitBean> dayVisiters(@Param("start") Timestamp start, @Param("end") Timestamp end);

    public List<AssetsBean> top10Assets(@Param("start") Timestamp start, @Param("end") Timestamp end);

    public List<AssetsBean> top10VaildAssets(@Param("start") Timestamp start, @Param("end") Timestamp end,@Param("sdb") int sdb,@Param("edb") int edb);

    public int countOldByTime(@Param("start") Timestamp start, @Param("end") Timestamp end);

    public int dayOldVisits(@Param("time") String time, @Param("assetsId") Integer assetsId);
}