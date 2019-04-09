package com.seeyoo.visit.mapper;

import com.seeyoo.visit.model.Assets;
import com.seeyoo.visit.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetsMapper extends MyMapper<Assets> {
    public void updateBatch(@Param("list") List<Assets> list);
}