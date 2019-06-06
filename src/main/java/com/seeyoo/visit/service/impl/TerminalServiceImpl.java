package com.seeyoo.visit.service.impl;

import com.seeyoo.visit.mapper2.TerminalMapper;
import com.seeyoo.visit.model.Terminal;
import com.seeyoo.visit.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalServiceImpl extends BaseService<Terminal> implements TerminalService {
    @Autowired
    private TerminalMapper terminalMapper;

    public Long tgroupIdByMac(String mac){
        return terminalMapper.tgroupIdByMac(mac);
    }
}
