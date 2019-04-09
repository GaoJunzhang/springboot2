package com.seeyoo.visit.controller;

import com.github.pagehelper.PageInfo;
import com.seeyoo.visit.bean.*;
import com.seeyoo.visit.model.Constant;
import com.seeyoo.visit.model.OldCustomers;
import com.seeyoo.visit.model.VisitProbe;
import com.seeyoo.visit.service.ConstantService;
import com.seeyoo.visit.service.OldCustomersService;
import com.seeyoo.visit.service.VisitProbeService;
import com.seeyoo.visit.util.StringTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@RestController
@RequestMapping("/record")
@Api(value = "VisitProbe", tags = {"Probe"})
public class VisitProbeController {
    @Autowired
    private VisitProbeService visitProbeService;

    @Autowired
    private OldCustomersService oldCustomersService;

    @Value("${vdb.vaildAdb}")
    private int Adb;

    @Value("${vdb.vaildBdb}")
    private int Bdb;

    @Value("${vdb.vaildPadb}")
    private int pAdb;

    @Value("${vdb.vaildPbdb}")
    private int pBdb;

    @Value("${vdb.residence.level}")
    private int rLevel;

    @Value("${vdb.residence.mlevel}")
    private int mLevel;

    @Autowired
    private ConstantService constantService;

    private static DecimalFormat df = new DecimalFormat("0.00");

    @RequestMapping(value = "/getVisitStastic", method = RequestMethod.GET)
    @ApiOperation(value = "客流详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "startDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "endDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "assetsId", value = "Assets id", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "page", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "rows", dataType = "Integer"),
    })
    public Map<String, Object> getVisitStastic(String startDate, String endDate, Integer assetsId, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "rows", defaultValue = "10") int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringTools.isEmptyString(startDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -6);
            startDate = StringTools.dateToString(calendar.getTime());
        }
        if (StringTools.isEmptyString(endDate)) {
            Calendar calendar = Calendar.getInstance();
            endDate = StringTools.dateToString(calendar.getTime());
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(StringTools.stringToDate(startDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(StringTools.stringToDate(endDate));
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.setTime(startCalendar.getTime());
        List<VisitRecordBean> visitRecordBeans = new ArrayList<VisitRecordBean>();
        Example example = new Example(VisitProbe.class);
        example.createCriteria().andBetween("beginTime", Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59")).andEqualTo("assetsId", assetsId);
        List<VisitProbe> btVisits = visitProbeService.selectByExample(example);
        PageInfo<VisitStatisBean> pages = visitProbeService.selectByPage(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"), assetsId, page, rows);
        List<VisitStatisBean> visitStatisBeans = pages.getList();
        Constant constant = constantService.selectByKey(1);
        for (VisitStatisBean visitStatisBean : visitStatisBeans) {
            VisitRecordBean visitRecordBean = null;
            if (constant != null) {
                visitRecordBean = transcatVisitStastic(btVisits, StringTools.dateToString(visitStatisBean.getVisitDate()), constant.getVaildAdb(), constant.getVaildeBdb(), constant.getVaildPadb(), constant.getVaildPbdb());
            } else {
                visitRecordBean = transcatVisitStastic(btVisits, StringTools.dateToString(visitStatisBean.getVisitDate()), Adb, Bdb, pAdb, pBdb);
            }
            visitRecordBean.setVisitDate(StringTools.dateToString(visitStatisBean.getVisitDate()));
            visitRecordBeans.add(visitRecordBean);
        }
        map.put("rows", visitRecordBeans);
        map.put("total", pages.getTotal());
        return map;
    }

    protected VisitRecordBean transcatVisitStastic(List<VisitProbe> list, String date, int aDb, int bDb, int pAdb, int pBdb) {
        VisitRecordBean visitRecordBean = new VisitRecordBean();
        if (list.size() <= 0) {
            return visitRecordBean;
        }
        int visitCount = 0;
        int vaildCount = 0;
        int passCount = 0;
        for (VisitProbe visitRecord : list) {
            if (date.equals(StringTools.timeStapm2Str(visitRecord.getBeginTime()))) {

                visitCount++;
                if (Math.abs(visitRecord.getDb()) > aDb && Math.abs(visitRecord.getDb()) < bDb) {
                    vaildCount++;
                }
                if (Math.abs(visitRecord.getDb()) > pAdb && Math.abs(visitRecord.getDb()) < pBdb) {
                    passCount++;
                }
            }
        }

        visitRecordBean.setVisitCount(visitCount);
        visitRecordBean.setVaildCount(vaildCount);
        visitRecordBean.setPassCount(passCount);
        visitRecordBean.setVaildRate(visitCount > 0 ? df.format((float) vaildCount * 100 / visitCount) + "%" : "0.00%");
        return visitRecordBean;
    }

    @RequestMapping(value = "/getVisitResidenceStastic", method = RequestMethod.GET)
    @ApiOperation(value = "驻留时长")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "startDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "endDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "assetsId", value = "Assets id", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "page", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "rows", dataType = "Integer"),
    })
    public Map<String, Object> getVisitResidenceStastic(String startDate, String endDate, Integer assetsId, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "rows", defaultValue = "10") int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringTools.isEmptyString(startDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -6);
            startDate = StringTools.dateToString(calendar.getTime());
        }
        if (StringTools.isEmptyString(endDate)) {
            Calendar calendar = Calendar.getInstance();
            endDate = StringTools.dateToString(calendar.getTime());
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(StringTools.stringToDate(startDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(StringTools.stringToDate(endDate));
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.setTime(startCalendar.getTime());
        PageInfo<VisitStatisBean> pages = visitProbeService.selectByPage(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"), assetsId, page, rows);
        List<VisitStatisBean> visitStatisBeans = pages.getList();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>(pages.getSize());
        Constant constant = constantService.selectByKey(1);
        int rlevel = 0;
        int mlevel = 0;
        if (constant != null) {
            rlevel = constant.getaLevel();
            mlevel = constant.getbLevel();
        } else {
            rlevel = rLevel;
            mlevel = mLevel;
        }
        for (VisitStatisBean visitStatisBean : visitStatisBeans) {
//            根据日期查询出当天所有人员访问记录
            int allMin = 0;
            int rCount = 0;
            int mCount = 0;//深度访问数量
            Map<String, String> map2 = new HashMap<String, String>();
            List<VisitTimeBean> visitRecords = visitProbeService.findDistinctByMacAndTime(StringTools.dateToString(visitStatisBean.getVisitDate()), assetsId);
            int allcount = 0;
            for (VisitTimeBean visitTimeBean : visitRecords) {
                int rMin = visitTimeBean.getVisitTime().intValue() > 0 ? visitTimeBean.getVisitTime().intValue() / 60 : 1;
                allcount += visitTimeBean.getVisitCount().intValue();
                allMin += rMin;
                if (rMin > rlevel) {
                    rCount++;
                }
                if (rMin > mlevel) {
                    mCount++;
                }
            }
            map2.put("allVisitCount", allcount > 0 ? allMin / allcount + "" : "0");
            map2.put("residenceCount", rCount + "");//天-长访数量
            map2.put("residenceRate", allcount > 0 ? df.format((float) rCount * 100 / allcount) + "" : "0");//天-长访率
            map2.put("shortVisitCount", allcount - rCount + "");//端访数量=总数量-长访数量
            map2.put("shortVisitRate", allcount > 0 ? df.format((float) (allcount - rCount) * 100 / allcount) + "" : "0");//短访率
            map2.put("visitDate", StringTools.dateToString(visitStatisBean.getVisitDate()));//日期
            map2.put("residenceTime", allMin + "");//天-长访总时间
            map2.put("averageTime", allcount > 0 ? allMin / allcount + "" : "0");
            map2.put("mCount", mCount + "");
            list.add(map2);
        }
        map.put("rows", list);
        map.put("total", pages.getTotal());
        return map;
    }

    @RequestMapping(value = "/getNewAndOldCustomers", method = RequestMethod.GET)
    @ApiOperation(value = "新老客户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "startDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "endDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "assetsId", value = "Assets id", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "page", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "rows", dataType = "Integer"),
    })
    public Map<String, Object> getNewAndOldCustomers(String startDate, String endDate, Integer assetsId, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "rows", defaultValue = "10") int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringTools.isEmptyString(startDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -6);
            startDate = StringTools.dateToString(calendar.getTime());
        }
        if (StringTools.isEmptyString(endDate)) {
            Calendar calendar = Calendar.getInstance();
            endDate = StringTools.dateToString(calendar.getTime());
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(StringTools.stringToDate(startDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(StringTools.stringToDate(endDate));
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.setTime(startCalendar.getTime());
        PageInfo<VisitStatisBean> pages = visitProbeService.selectByPage(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"), assetsId, page, rows);
        List<VisitStatisBean> visitStatisBeans = pages.getList();
        List<OldCustomers> regularCustomers = oldCustomersService.selectAll();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>(pages.getSize());
        for (VisitStatisBean visitStatisBean : visitStatisBeans) {
            Map<String, String> map2 = new HashMap<String, String>();
            int oldCount = 0;
            int allCount = Integer.parseInt(visitStatisBean.getVisitCount() + "");
            List<DayVisitBean> dayVisitBeans = visitProbeService.dayVisits(StringTools.dateToString(visitStatisBean.getVisitDate()), assetsId);
            if (dayVisitBeans != null) {

                for (DayVisitBean dayVisitBean : dayVisitBeans) {
                    if (isOldCustomer(regularCustomers, dayVisitBean.getMac(), dayVisitBean.getTime())) {
                        oldCount += Integer.parseInt(dayVisitBean.getVisitDayCount() + "");
                    }
                }
            }
            map2.put("visitDate", StringTools.dateToString(visitStatisBean.getVisitDate()));//日期
            map2.put("newCustomerRate", allCount > 0 ? df.format((float) (allCount - oldCount) * 100 / allCount) : "0");//新客比率
            map2.put("newCustomerCount", allCount - oldCount + "");//新客数量
            map2.put("oldCustomerRate", allCount > 0 ? df.format((float) oldCount * 100 / allCount) + "" : "0");
            map2.put("oldCustomerCount", oldCount + "");
            list.add(map2);
        }
        map.put("rows", list);
        map.put("total", pages.getTotal());
        return map;
    }

    public boolean isOldCustomer(List<OldCustomers> list, String mac, Timestamp time) {
        if (list.size() <= 0) {
            return false;
        }
        boolean flag = false;
        for (OldCustomers oldCustomers : list) {
            long diffTime = (time.getTime() - oldCustomers.getCreateTime().getTime()) / 3600000;
            if (oldCustomers.getMac().equals(mac) && diffTime >= 12) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @RequestMapping(value = "/getAllVisitStatis", method = RequestMethod.GET)
    @ApiOperation(value = "访问统计 ")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "startDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "endDate", dataType = "String"),
    })
    public Map<String, Object> getAllVisitStatis(@RequestParam(name = "startDate", required = false) String startDate, @RequestParam(name = "endDate", required = false) String endDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringTools.isEmptyString(startDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -6);
            startDate = StringTools.dateToString(calendar.getTime());
        }
        if (StringTools.isEmptyString(endDate)) {
            Calendar calendar = Calendar.getInstance();
            endDate = StringTools.dateToString(calendar.getTime());
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(StringTools.stringToDate(startDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(StringTools.stringToDate(endDate));
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.setTime(startCalendar.getTime());
        List<VisitRecordBean> visitRecordBeans = new ArrayList<VisitRecordBean>();
        Constant constant = constantService.selectByKey(1);
        List<VisitStatisBean> visitStatisBeans = visitProbeService.dayVisitCount(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"));
        List<VisitStatisBean> visitVaildStatisBeans = null;
        if (constant != null) {
            visitVaildStatisBeans = visitProbeService.dayVisitVaildCount(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"), constant.getVaildAdb(), constant.getVaildeBdb());
        } else {
            visitVaildStatisBeans = visitProbeService.dayVisitVaildCount(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"), Adb, Bdb);
        }
        while (startCalendar.compareTo(endCalendar) <= 0) {
            VisitRecordBean visitRecordBean = new VisitRecordBean();
            int dayCount = 0;
            int dayVaildCount = 0;
            for (VisitStatisBean visitStatisBean : visitStatisBeans) {
                if (visitStatisBean.getVisitDate().equals(startCalendar.getTime())) {
                    dayCount += visitStatisBean.getVisitCount().intValue();
                }
            }
            for (VisitStatisBean visitStatisBean : visitVaildStatisBeans) {
                if (visitStatisBean.getVisitDate().equals(startCalendar.getTime())) {
                    dayVaildCount += visitStatisBean.getVisitCount().intValue();
                }
            }
            visitRecordBean.setVisitDate(StringTools.dateToString(startCalendar.getTime()));
            visitRecordBean.setVisitCount(dayCount);
            visitRecordBean.setVaildCount(dayVaildCount);
            visitRecordBeans.add(visitRecordBean);
            startCalendar.add(Calendar.DATE, 1);
        }
        int visitAllTime = visitProbeService.getAllVisitTime(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"));//所有访问时间
        List<OldCustomers> regularCustomers = oldCustomersService.selectAll();//全部老客户
        List<DayVisitBean> allCustomer = visitProbeService.dayVisiters(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"));
        int oldCustomer = 0;
        if (allCustomer != null) {
            for (DayVisitBean dayVisitBean : allCustomer) {
                if (isOldCustomer(regularCustomers, dayVisitBean.getMac(), dayVisitBean.getTime())) {
                    oldCustomer++;
                }
            }
        }
        map.put("visitAllTime", visitAllTime);
        map.put("list", visitRecordBeans);
        map.put("oldCustomer", oldCustomer);
        return map;
    }

    @RequestMapping(value = "/getTop10Assets", method = RequestMethod.GET)
    @ApiOperation(value = "资产排行Top10")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "startDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "endDate", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "0 or 1", dataType = "int"),
    })
    public Map<String, Object> getTop10Assets(String startDate, String endDate, @RequestParam(name = "type", defaultValue = "0", required = false) int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringTools.isEmptyString(startDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -6);
            startDate = StringTools.dateToString(calendar.getTime());
        }
        if (StringTools.isEmptyString(endDate)) {
            Calendar calendar = Calendar.getInstance();
            endDate = StringTools.dateToString(calendar.getTime());
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(StringTools.stringToDate(startDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(StringTools.stringToDate(endDate));
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.setTime(startCalendar.getTime());
        List<AssetsBean> assetsBeans = null;
        if (type == 1) {
            assetsBeans = visitProbeService.top10Assets(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"));
        } else {
            Constant constant = constantService.selectByKey(1);
            if (constant != null) {
                assetsBeans = visitProbeService.top10VaildAssets(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"), constant.getVaildAdb(), constant.getVaildeBdb());
            } else {

                assetsBeans = visitProbeService.top10VaildAssets(Timestamp.valueOf(startDate + " 00:00:00"), Timestamp.valueOf(endDate + " 23:59:59"), Adb, Bdb);
            }
        }
        map.put("rows", assetsBeans);
        map.put("total", assetsBeans.size());
        return map;
    }
}
