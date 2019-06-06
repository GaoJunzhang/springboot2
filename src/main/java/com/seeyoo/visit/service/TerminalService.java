package com.seeyoo.visit.service;

import com.seeyoo.visit.model.Terminal;

public interface TerminalService extends IService<Terminal> {

    public Long tgroupIdByMac(String mac);
}
