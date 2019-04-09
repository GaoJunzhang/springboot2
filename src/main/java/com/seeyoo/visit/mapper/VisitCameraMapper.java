package com.seeyoo.visit.mapper;

import com.seeyoo.visit.bean.AssetsBean;
import com.seeyoo.visit.bean.VisitCameraBean;
import com.seeyoo.visit.model.VisitCamera;
import com.seeyoo.visit.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface VisitCameraMapper extends MyMapper<VisitCamera> {
    public List<VisitCameraBean> dayVisitCount(@Param("start") Timestamp start, @Param("end") Timestamp end, @Param("assetsId") int assetsId);
    public List<AssetsBean> top10MemberAssets(@Param("start") Timestamp start,  @Param("end")Timestamp end);
    public int countByAgeBetweenAndStampBetween( @Param("start")Timestamp start, @Param("end")Timestamp end,@Param("startAge")int startAge,@Param("endAge")int endAge,@Param("assetsId")int assetsId);
}