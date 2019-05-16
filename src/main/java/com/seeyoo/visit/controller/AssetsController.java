package com.seeyoo.visit.controller;

import com.github.pagehelper.PageInfo;
import com.seeyoo.visit.bean.TerminalBean;
import com.seeyoo.visit.model.Assets;
import com.seeyoo.visit.result.ResultVO;
import com.seeyoo.visit.service.AssetsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assets")
@Api(value = "Assets", tags = {"Assets"})
public class AssetsController {
    @Autowired
    private AssetsService assetsService;

    @RequestMapping(value = "/getAssets", method = RequestMethod.GET)
    @ApiOperation(value = "资产")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mac", value = "mac", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "name", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "page", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "rows", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "sortType", value = "sortType", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "sortValue", value = "sortValue", dataType = "String"),
    })
    public Map<String, Object> getAssets(Assets assets, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "rows", defaultValue = "10") int rows,
                                         @RequestParam(name = "sortType", defaultValue = "desc") String sortType, @RequestParam(name = "sortValue", defaultValue = "time") String sortValue) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<Assets> pageInfo = assetsService.selectByPage(assets, page, rows);
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @RequestMapping(value = "/postAssets", method = RequestMethod.POST)
    @ApiOperation(value = "更新资产")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "name", dataType = "String"),
    })
    public Object postAssets(Assets assets) {
        if (assets.getId() == null) {
            return ResultVO.getFailed("Assets's field 'id' couldn't be null");
        }
        Assets assets1 = assetsService.selectByKey(assets.getId());
        assets1.setName(assets.getName());
        assetsService.updateAll(assets1);
        return ResultVO.getSuccess("Success");
    }

    @RequestMapping(value = "/bacthUpdateAssets", method = RequestMethod.POST)
    @ApiOperation(value = "批量更新")
    @ApiImplicitParam(paramType = "list", name = "list", value = "list", dataType = "Java.lang.List")
    public ResultVO bacthUpdateAssets(List<TerminalBean> tlist) {
        if (tlist.size() > 0) {
            Example example = new Example(Assets.class);
            example.createCriteria().andIsNull("tgroupId");
            List<Assets> list = assetsService.selectByExample(example);
            List<Assets> list1 = new ArrayList<Assets>(list.size());
            Map<String,TerminalBean> map = new HashMap<String, TerminalBean>();
            for (TerminalBean terminalBean : tlist){
                map.put(terminalBean.getMac(),terminalBean);
            }
            for (Assets assets : list) {
                if (map.containsKey(assets.getMac())){
                    Assets assets1 = new Assets();
                    assets1.setTgroupId(map.get(assets.getMac()).getTgroupId());
                    assets1.setMac(assets.getMac());
                    list1.add(assets1);
                }
            }
            try {
                assetsService.updateBatch(list1);
            } catch (Exception e) {
                return ResultVO.getFailed("exception", e);
            }
        }
        return ResultVO.getSuccess("success");
    }

    @RequestMapping(value = "getAssetsByMac",method = RequestMethod.POST,params = "macs")
    @ApiOperation(value = "资产信息")
    @ApiImplicitParam(paramType = "arry",name = "macs",value = "Array",dataType = "Array")
    public ResultVO getAssetsByMac(@RequestParam("macs") List<String> macs,Short type){
        if (macs.size()<=0){
            return ResultVO.getFailed("Terminal'macs is null");
        }
        Example example = new Example(Assets.class);
        if (type!=null){
            example.createCriteria().andIn("mac",macs).andEqualTo("type",type);
        }else {
            example.createCriteria().andIn("mac",macs);
        }
        List<Assets> assets = assetsService.selectByExample(example);
        return ResultVO.getSuccess("success",assets);
    }
}
