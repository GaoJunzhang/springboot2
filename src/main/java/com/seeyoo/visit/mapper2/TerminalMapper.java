package com.seeyoo.visit.mapper2;

import com.seeyoo.visit.model.Terminal;
import com.seeyoo.visit.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface TerminalMapper extends MyMapper<Terminal> {
    public Long tgroupIdByMac(@Param("mac") String mac);

}