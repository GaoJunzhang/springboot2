package com.seeyoo.visit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seeyoo.visit.mapper.AssetsMapper;
import com.seeyoo.visit.model.Assets;
import com.seeyoo.visit.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service("assetsService")
public class AssetsServiceImpl extends BaseService<Assets> implements AssetsService {

    @Autowired
    private AssetsMapper assetsMapper;

    public List<Assets> assets(Integer id, String name, String mac, Short type) {
        Example example = new Example(Assets.class);
        Example.Criteria criteria = example.createCriteria();
        if (id != null) {
            criteria.andEqualTo("id", id);
        }
        if (!StringUtil.isNotEmpty(name)) {

            criteria.andLike("name", "%" + name + "%");
        }
        if (!StringUtil.isNotEmpty(mac)) {
            criteria.andLike("mac", "%" + mac + "%");
        }
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        List<Assets> assetsList = selectByExample(example);
        if (assetsList.size() > 0) {
            return assetsList;
        }
        return null;
    }

    public PageInfo<Assets> selectByPage(Assets assets, int page, int size) {
        Example example = new Example(Assets.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(assets.getName())) {
            criteria.andLike("name", "%" + assets.getName() + "%");
        }
        if (assets.getId() != null) {
            criteria.andEqualTo("id", assets.getId());
        }
        if (assets.getType() != null) {
            criteria.andEqualTo("type", assets.getType());
        }
        if (StringUtil.isNotEmpty(assets.getMac())) {
            criteria.andLike("mac", "%" + assets.getMac() + "%");
        }
        if (assets.getTgroupId()!=null){
            criteria.andEqualTo("tgroupId",assets.getTgroupId());
        }
        //分页查询
        PageHelper.startPage(page, size);
        List<Assets> userList = assetsMapper.selectByExample(example);
        return new PageInfo<>(userList);
    }

    public void updateBatch(List<Assets> list){
        assetsMapper.updateBatch(list);
    }
}
