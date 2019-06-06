package com.seeyoo.visit.service;

import com.seeyoo.visit.bean.TgroupBean;
import com.seeyoo.visit.model.Tgroup;

public interface TgroupService extends IService<Tgroup> {
    public TgroupBean codeByMac(String mac);
}
