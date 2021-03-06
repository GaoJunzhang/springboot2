package com.seeyoo.visit.service;

import com.seeyoo.visit.bean.AssetsBean;
import com.seeyoo.visit.bean.VisitCameraBean;
import com.seeyoo.visit.model.VisitCamera;

import java.sql.Timestamp;
import java.util.List;

public interface VisitCameraService extends IService<VisitCamera> {
    public List<VisitCameraBean> dayVisitCount(Timestamp startDate, Timestamp endDate, int[] idList,String code);

    public int countByAgeBetweenAndStampBetween(Timestamp start, Timestamp end, int startAge, int endAge, int[] idList,String code);

    public List<AssetsBean> top10MemberAssets(Timestamp start, Timestamp end,int[] idList,String code);
}
