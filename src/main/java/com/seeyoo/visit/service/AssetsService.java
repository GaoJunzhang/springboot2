package com.seeyoo.visit.service;

import com.github.pagehelper.PageInfo;
import com.seeyoo.visit.model.Assets;

import java.util.List;

public interface AssetsService extends IService<Assets> {
    public List<Assets> assets(Integer id, String name, String mac, Short type);

    public PageInfo<Assets> selectByPage(Assets assets, int start, int length);

    public void updateBatch(List<Assets> list);
}
