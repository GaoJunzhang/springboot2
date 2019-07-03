package com.seeyoo.visit.controller;

import com.seeyoo.visit.model.Assets;
import com.seeyoo.visit.model.OldCustomers;
import com.seeyoo.visit.model.VisitCamera;
import com.seeyoo.visit.model.VisitProbe;
import com.seeyoo.visit.result.ResultVO;
import com.seeyoo.visit.service.AssetsService;
import com.seeyoo.visit.service.OldCustomersService;
import com.seeyoo.visit.service.VisitCameraService;
import com.seeyoo.visit.service.VisitProbeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/web")
@Api(value = "api web", tags = {"ApiWeb"})
public class ApiController {
    @Autowired
    private VisitCameraService visitCameraService;
    @Autowired
    private VisitProbeService visitProbeService;
    @Autowired
    private OldCustomersService oldCustomersService;
    @Autowired
    private AssetsService assetsService;
    @Value("${vdb.oldTime}")
    private int oldTime;

    @ApiOperation(value = "记录探针")
    @ApiImplicitParam(name = "jsonObject", value = "Json Object", required = true, dataType = "JsonObj", paramType = "path")
    @RequestMapping(value = "/postVisitorRecord", method = RequestMethod.POST, produces = "application/json")
    public Object postVisitorRecord(@RequestBody String arryObj) {
        JSONObject jsonObject = JSONObject.fromObject(arryObj);
        if (jsonObject == null || !jsonObject.containsKey("list")) {
            return ResultVO.getFailed("Request parameter no allow null!");
        }
        if (!jsonObject.containsKey("mac")) {
            return ResultVO.getFailed("Terminal Mac Unavailable,Field 'mac' is Null!");
        }
        Example example = new Example(Assets.class);
        example.createCriteria().andEqualTo("mac", jsonObject.get("mac")).andEqualTo("type", 0);
        List<Assets> assetses = assetsService.selectByExample(example);
        int assetsId = 0;
        if (assetses.size() <= 0) {
            Assets assets = new Assets();
            assets.setMac(jsonObject.get("mac") + "");
            assets.setName(jsonObject.get("mac") + "");
            assets.setTime(new Timestamp(System.currentTimeMillis()));
            assets.setType((short) 0);
            assetsService.save(assets);
            assetsId = assets.getId();
        } else {
            assetsId = assetses.get(0).getId();
        }
        JSONArray list = JSONArray.fromObject(jsonObject.get("list"));
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        JSONObject obj = null;
        JSONObject macObj = null;
        List<OldCustomers> OldVisitProbes = new ArrayList<>();
        List<VisitProbe> newVisitProbes = new ArrayList<>();
        List<VisitProbe> newUVisitProbes = new ArrayList<>();
        try {
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    obj = JSONObject.fromObject(list.get(i));
                    time1 = Timestamp.valueOf(obj.get("time") + "");
                    if (obj.containsKey("macs")) {
                        JSONArray macsArry = JSONArray.fromObject(obj.get("macs"));
                        //老用户

                        //新用户
                        for (int j = 0; j < macsArry.size(); j++) {

                            macObj = JSONObject.fromObject(macsArry.get(j));
//                            VisitProbe visitProbe = visitProbeService.selectByMacAndTimeAndAssetsId(macObj.get("mac") + "",Timestamp.valueOf(macObj.get("begin")+""),assetsId);
                            Example example2 = new Example(VisitProbe.class);
                            example2.createCriteria().andEqualTo("mac", macObj.get("mac")).andEqualTo("beginTime", Timestamp.valueOf(macObj.get("begin") + "")).andEqualTo("assetsId", assetsId);
                            List<VisitProbe> visitProbes = visitProbeService.selectByExample(example2);
                            VisitProbe visitProbe = null;
                            if (visitProbes.size() > 0) {
                                visitProbe = visitProbes.get(0);
                            }
                            Example example1 = new Example(OldCustomers.class);
                            example1.createCriteria().andEqualTo("mac", macObj.get("mac"));
                            List<OldCustomers> oldCustomers = oldCustomersService.selectByExample(example1);
                            short isOld = 0;
                            if (oldCustomers.size() <= 0) {
                                OldCustomers oldCustomers1 = new OldCustomers();
                                oldCustomers1.setCreateTime(time1);
                                oldCustomers1.setMac(macObj.get("mac") + "");
                                OldVisitProbes.add(oldCustomers1);
//                                oldCustomersService.save(oldCustomers1);//TODO
                            } else {
                                OldCustomers oldTmp = oldCustomers.get(0);
                                if (time1.getTime() - oldTmp.getCreateTime().getTime() >= oldTime) {
                                    isOld = 1;
                                }
                            }
                            if (visitProbe == null) {
                                visitProbe = new VisitProbe();
                                visitProbe.setBeginTime(Timestamp.valueOf(macObj.get("begin") + ""));
                                visitProbe.setEndTime(Timestamp.valueOf(macObj.get("end") + ""));
                                visitProbe.setMac(macObj.get("mac") + "");
                                visitProbe.setAssetsId(assetsId);
                                visitProbe.setDb(Integer.parseInt(macObj.get("db") + ""));
                                visitProbe.setIsOld(isOld);
                                visitProbe.setTime(new Timestamp(System.currentTimeMillis()));
                                newVisitProbes.add(visitProbe);
//                                visitProbeService.save(visitProbe);//TODO
                            } else {
                                if (visitProbe.getDb() < Integer.parseInt(macObj.get("db") + "")) {

                                    visitProbe.setDb(Integer.parseInt(macObj.get("db") + ""));
                                }
                                visitProbe.setEndTime(Timestamp.valueOf(macObj.get("end") + ""));
                                visitProbe.setIsOld(isOld);
                                visitProbe.setTime(new Timestamp(System.currentTimeMillis()));
                                newUVisitProbes.add(visitProbe);
//                                visitProbeService.updateNotNull(visitProbe);//TODO
                            }
                        }

                    }
                }
            }
            if (OldVisitProbes.size() > 0) {

                oldCustomersService.saveList(OldVisitProbes);//批量保存老用户
            }
            if (newVisitProbes.size() > 0) {

                visitProbeService.saveList(newVisitProbes);//批量保存新用户
            }
            if (newUVisitProbes.size() > 0) {

                visitProbeService.batchUpdate(newUVisitProbes);//批量更新新用户
            }
        } catch (NumberFormatException e) {
            return ResultVO.getFailed("Request Exception!", e);
        }
        return ResultVO.getSuccess("Success!");
    }

    @ApiOperation(value = "记录摄像头", notes = "Create an visit camera")
    @ApiImplicitParam(name = "jsonObject", value = "JSONObject", required = true, dataType = "JsonObj", paramType = "path")
    @RequestMapping(value = "/postVisitorMember", method = RequestMethod.POST)
    public Object postVisitorMember(@RequestBody String arry) {
        try {
            JSONObject jsonObject = JSONObject.fromObject(arry);
            if (jsonObject == null || !jsonObject.containsKey("list")) {
                return ResultVO.getFailed("Request parameters no allow null");
            }
            JSONArray list = JSONArray.fromObject(jsonObject.get("list"));
            Example example = new Example(Assets.class);
            example.createCriteria().andEqualTo("mac", jsonObject.get("mac")).andEqualTo("type", 1);
            List<Assets> assetses = assetsService.selectByExample(example);
            int assetsId = 0;
            if (assetses.size() <= 0) {
                Assets assets = new Assets();
                assets.setMac(jsonObject.get("mac") + "");
                assets.setName(jsonObject.get("mac") + "");
                assets.setTime(new Timestamp(System.currentTimeMillis()));
                assets.setType((short) 1);
                assetsService.save(assets);
                assetsId = assets.getId();
            } else {
                assetsId = assetses.get(0).getId();
            }
            for (int i = 0; i < list.size(); i++) {
                JSONObject mObj = JSONObject.fromObject(list.get(i));
                VisitCamera visitCamera = new VisitCamera();
                visitCamera.setAge(Integer.parseInt(mObj.get("age") + ""));
                visitCamera.setGender(Short.parseShort(mObj.get("gender") + ""));
                visitCamera.setBeauty(Integer.parseInt(mObj.get("beauty") + ""));
                visitCamera.setStamp(Timestamp.valueOf(mObj.get("stamp") + ""));
                visitCamera.setTime(Timestamp.valueOf(mObj.get("stamp") + ""));
                visitCamera.setAssetsId(assetsId);
                visitCamera.setStay(Integer.parseInt(mObj.get("stay") + ""));
                visitCamera.setVisitId(Long.parseLong(mObj.get("id") + ""));
                visitCameraService.save(visitCamera);
            }
        } catch (NumberFormatException e) {
            return ResultVO.getFailed("Request Exception", e);
        }
        return ResultVO.getSuccess("Success");
    }
}
