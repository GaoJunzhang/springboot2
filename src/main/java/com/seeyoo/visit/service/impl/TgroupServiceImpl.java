package com.seeyoo.visit.service.impl;

import com.seeyoo.visit.bean.TgroupBean;
import com.seeyoo.visit.mapper2.TgroupMapper;
import com.seeyoo.visit.model.Tgroup;
import com.seeyoo.visit.service.TgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TgroupServiceImpl extends BaseService<Tgroup> implements TgroupService {
    @Autowired
    private TgroupMapper tgroupMapper;
    public TgroupBean codeByMac(String mac){
        return tgroupMapper.codeByMac(mac);
    }
}
