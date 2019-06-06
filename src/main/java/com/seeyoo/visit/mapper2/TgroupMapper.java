package com.seeyoo.visit.mapper2;

import com.seeyoo.visit.bean.TgroupBean;
import com.seeyoo.visit.model.Tgroup;
import com.seeyoo.visit.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface TgroupMapper extends MyMapper<Tgroup> {
    public TgroupBean codeByMac(@Param("mac") String mac);
}