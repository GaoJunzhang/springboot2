package com.seeyoo.visit.service.impl;

import com.seeyoo.visit.bean.AssetsBean;
import com.seeyoo.visit.bean.VisitCameraBean;
import com.seeyoo.visit.mapper.VisitCameraMapper;
import com.seeyoo.visit.model.VisitCamera;
import com.seeyoo.visit.service.VisitCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("visitCameraService")
public class VisitCameraServiceImpl extends BaseService<VisitCamera> implements VisitCameraService {
    @Autowired
    private VisitCameraMapper visitCameraMapper;


    public List<VisitCameraBean> dayVisitCount(Timestamp startDate, Timestamp endDate,int[] idList) {
        return visitCameraMapper.dayVisitCount(startDate, endDate, idList);
    }

    public int countByAgeBetweenAndStampBetween(Timestamp start, Timestamp end, int startAge, int endAge,int[] idList) {
        return visitCameraMapper.countByAgeBetweenAndStampBetween(start, end, startAge, endAge, idList);
    }

    public List<AssetsBean> top10MemberAssets(Timestamp start, Timestamp end,int[] idList) {
        return visitCameraMapper.top10MemberAssets(start, end,idList);
    }
}
