package com.seeyoo.visit.model;

import java.util.Date;
import javax.persistence.*;

public class Terminal {
    @Id
    private Integer id;

    @Column(name = "tgroup_id")
    private Long tgroupId;

    private String name;

    private String mac;

    @Column(name = "devState")
    private String devstate;

    @Column(name = "dlFileSize")
    private String dlfilesize;

    @Column(name = "useableSpace")
    private String useablespace;

    @Column(name = "diskSpace")
    private String diskspace;

    @Column(name = "playStatus")
    private String playstatus;

    @Column(name = "taskName")
    private String taskname;

    @Column(name = "templateID")
    private String templateid;

    @Column(name = "fileStatus")
    private String filestatus;

    @Column(name = "playContent")
    private String playcontent;

    @Column(name = "imd_update_time")
    private Date imdUpdateTime;

    @Column(name = "serverIp")
    private String serverip;

    @Column(name = "serverMac")
    private String servermac;

    private String dev;

    @Column(name = "nkVersion")
    private String nkversion;

    @Column(name = "appVersion")
    private String appversion;

    @Column(name = "devType")
    private String devtype;

    @Column(name = "netType")
    private String nettype;

    private String dhcp;

    @Column(name = "devIP")
    private String devip;

    @Column(name = "devSubMask")
    private String devsubmask;

    @Column(name = "devGateWay")
    private String devgateway;

    @Column(name = "wlanName")
    private String wlanname;

    @Column(name = "wlanEncryption")
    private String wlanencryption;

    @Column(name = "wlanPasswd")
    private String wlanpasswd;

    @Column(name = "mcsAddr")
    private String mcsaddr;

    @Column(name = "mcsDomain")
    private String mcsdomain;

    @Column(name = "mcsPort")
    private String mcsport;

    @Column(name = "subAddr")
    private String subaddr;

    @Column(name = "subDomain")
    private String subdomain;

    @Column(name = "subPort")
    private String subport;

    @Column(name = "mainFtpAddr")
    private String mainftpaddr;

    @Column(name = "mainFtpDomain")
    private String mainftpdomain;

    @Column(name = "mainFtpPort")
    private String mainftpport;

    @Column(name = "mainFtpUser")
    private String mainftpuser;

    @Column(name = "mainFtpPasswd")
    private String mainftppasswd;

    @Column(name = "subFtpAddr")
    private String subftpaddr;

    @Column(name = "subFtpDomain")
    private String subftpdomain;

    @Column(name = "subFtpPort")
    private String subftpport;

    @Column(name = "subFtpUser")
    private String subftpuser;

    @Column(name = "subFtpPasswd")
    private String subftppasswd;

    @Column(name = "trackAddr")
    private String trackaddr;

    @Column(name = "trackPort")
    private String trackport;

    @Column(name = "satAddr")
    private String sataddr;

    @Column(name = "dwnSpeed")
    private String dwnspeed;

    @Column(name = "pwrOnOffList")
    private String pwronofflist;

    @Column(name = "sec1Start")
    private String sec1start;

    @Column(name = "sec2Start")
    private String sec2start;

    @Column(name = "sec3Start")
    private String sec3start;

    @Column(name = "sec4Start")
    private String sec4start;

    @Column(name = "sec5Start")
    private String sec5start;

    @Column(name = "sec1End")
    private String sec1end;

    @Column(name = "sec2End")
    private String sec2end;

    @Column(name = "sec3End")
    private String sec3end;

    @Column(name = "sec4End")
    private String sec4end;

    @Column(name = "sec5End")
    private String sec5end;

    private String w1;

    private String w2;

    private String w3;

    private String w4;

    private String w5;

    private String w6;

    private String w7;

    private String d1;

    private String d2;

    private String d3;

    private String d4;

    private String d5;

    private String d6;

    private String d7;

    private String d8;

    private String g1;

    private String g2;

    private String g3;

    private String g4;

    private String g5;

    @Column(name = "timeMode")
    private String timemode;

    @Column(name = "downloadTime")
    private String downloadtime;

    @Column(name = "timeShow")
    private String timeshow;

    private String volume;

    @Column(name = "brightnessBL")
    private String brightnessbl;

    @Column(name = "brightnessPIC")
    private String brightnesspic;

    private String contrast;

    private String saturation;

    @Column(name = "dayNum")
    private String daynum;

    @Column(name = "dirDay")
    private String dirday;

    private String mode;

    @Column(name = "connect_status")
    private Byte connectStatus;

    @Column(name = "connect_time")
    private Date connectTime;

    @Column(name = "disconnect_time")
    private Date disconnectTime;

    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "preDwn")
    private String predwn;

    private String p2p;

    private String sync;

    @Column(name = "thirdTask")
    private String thirdtask;

    @Column(name = "thirdPath")
    private String thirdpath;

    @Column(name = "thirdTimeout")
    private String thirdtimeout;

    @Column(name = "thirdVersion")
    private String thirdversion;

    @Column(name = "torg_id")
    private Integer torgId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "default_task_id")
    private Long defaultTaskId;

    @Column(name = "tel_enable")
    private Short telEnable;

    private String tel;

    @Column(name = "ad_use_count")
    private Integer adUseCount;

    @Column(name = "ad_count")
    private Integer adCount;

    private Long rx;

    private Long tx;

    private String iccid;

    @Column(name = "default_task_date")
    private Date defaultTaskDate;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return tgroup_id
     */
    public Long getTgroupId() {
        return tgroupId;
    }

    /**
     * @param tgroupId
     */
    public void setTgroupId(Long tgroupId) {
        this.tgroupId = tgroupId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * @return devState
     */
    public String getDevstate() {
        return devstate;
    }

    /**
     * @param devstate
     */
    public void setDevstate(String devstate) {
        this.devstate = devstate;
    }

    /**
     * @return dlFileSize
     */
    public String getDlfilesize() {
        return dlfilesize;
    }

    /**
     * @param dlfilesize
     */
    public void setDlfilesize(String dlfilesize) {
        this.dlfilesize = dlfilesize;
    }

    /**
     * @return useableSpace
     */
    public String getUseablespace() {
        return useablespace;
    }

    /**
     * @param useablespace
     */
    public void setUseablespace(String useablespace) {
        this.useablespace = useablespace;
    }

    /**
     * @return diskSpace
     */
    public String getDiskspace() {
        return diskspace;
    }

    /**
     * @param diskspace
     */
    public void setDiskspace(String diskspace) {
        this.diskspace = diskspace;
    }

    /**
     * @return playStatus
     */
    public String getPlaystatus() {
        return playstatus;
    }

    /**
     * @param playstatus
     */
    public void setPlaystatus(String playstatus) {
        this.playstatus = playstatus;
    }

    /**
     * @return taskName
     */
    public String getTaskname() {
        return taskname;
    }

    /**
     * @param taskname
     */
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    /**
     * @return templateID
     */
    public String getTemplateid() {
        return templateid;
    }

    /**
     * @param templateid
     */
    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    /**
     * @return fileStatus
     */
    public String getFilestatus() {
        return filestatus;
    }

    /**
     * @param filestatus
     */
    public void setFilestatus(String filestatus) {
        this.filestatus = filestatus;
    }

    /**
     * @return playContent
     */
    public String getPlaycontent() {
        return playcontent;
    }

    /**
     * @param playcontent
     */
    public void setPlaycontent(String playcontent) {
        this.playcontent = playcontent;
    }

    /**
     * @return imd_update_time
     */
    public Date getImdUpdateTime() {
        return imdUpdateTime;
    }

    /**
     * @param imdUpdateTime
     */
    public void setImdUpdateTime(Date imdUpdateTime) {
        this.imdUpdateTime = imdUpdateTime;
    }

    /**
     * @return serverIp
     */
    public String getServerip() {
        return serverip;
    }

    /**
     * @param serverip
     */
    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    /**
     * @return serverMac
     */
    public String getServermac() {
        return servermac;
    }

    /**
     * @param servermac
     */
    public void setServermac(String servermac) {
        this.servermac = servermac;
    }

    /**
     * @return dev
     */
    public String getDev() {
        return dev;
    }

    /**
     * @param dev
     */
    public void setDev(String dev) {
        this.dev = dev;
    }

    /**
     * @return nkVersion
     */
    public String getNkversion() {
        return nkversion;
    }

    /**
     * @param nkversion
     */
    public void setNkversion(String nkversion) {
        this.nkversion = nkversion;
    }

    /**
     * @return appVersion
     */
    public String getAppversion() {
        return appversion;
    }

    /**
     * @param appversion
     */
    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    /**
     * @return devType
     */
    public String getDevtype() {
        return devtype;
    }

    /**
     * @param devtype
     */
    public void setDevtype(String devtype) {
        this.devtype = devtype;
    }

    /**
     * @return netType
     */
    public String getNettype() {
        return nettype;
    }

    /**
     * @param nettype
     */
    public void setNettype(String nettype) {
        this.nettype = nettype;
    }

    /**
     * @return dhcp
     */
    public String getDhcp() {
        return dhcp;
    }

    /**
     * @param dhcp
     */
    public void setDhcp(String dhcp) {
        this.dhcp = dhcp;
    }

    /**
     * @return devIP
     */
    public String getDevip() {
        return devip;
    }

    /**
     * @param devip
     */
    public void setDevip(String devip) {
        this.devip = devip;
    }

    /**
     * @return devSubMask
     */
    public String getDevsubmask() {
        return devsubmask;
    }

    /**
     * @param devsubmask
     */
    public void setDevsubmask(String devsubmask) {
        this.devsubmask = devsubmask;
    }

    /**
     * @return devGateWay
     */
    public String getDevgateway() {
        return devgateway;
    }

    /**
     * @param devgateway
     */
    public void setDevgateway(String devgateway) {
        this.devgateway = devgateway;
    }

    /**
     * @return wlanName
     */
    public String getWlanname() {
        return wlanname;
    }

    /**
     * @param wlanname
     */
    public void setWlanname(String wlanname) {
        this.wlanname = wlanname;
    }

    /**
     * @return wlanEncryption
     */
    public String getWlanencryption() {
        return wlanencryption;
    }

    /**
     * @param wlanencryption
     */
    public void setWlanencryption(String wlanencryption) {
        this.wlanencryption = wlanencryption;
    }

    /**
     * @return wlanPasswd
     */
    public String getWlanpasswd() {
        return wlanpasswd;
    }

    /**
     * @param wlanpasswd
     */
    public void setWlanpasswd(String wlanpasswd) {
        this.wlanpasswd = wlanpasswd;
    }

    /**
     * @return mcsAddr
     */
    public String getMcsaddr() {
        return mcsaddr;
    }

    /**
     * @param mcsaddr
     */
    public void setMcsaddr(String mcsaddr) {
        this.mcsaddr = mcsaddr;
    }

    /**
     * @return mcsDomain
     */
    public String getMcsdomain() {
        return mcsdomain;
    }

    /**
     * @param mcsdomain
     */
    public void setMcsdomain(String mcsdomain) {
        this.mcsdomain = mcsdomain;
    }

    /**
     * @return mcsPort
     */
    public String getMcsport() {
        return mcsport;
    }

    /**
     * @param mcsport
     */
    public void setMcsport(String mcsport) {
        this.mcsport = mcsport;
    }

    /**
     * @return subAddr
     */
    public String getSubaddr() {
        return subaddr;
    }

    /**
     * @param subaddr
     */
    public void setSubaddr(String subaddr) {
        this.subaddr = subaddr;
    }

    /**
     * @return subDomain
     */
    public String getSubdomain() {
        return subdomain;
    }

    /**
     * @param subdomain
     */
    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    /**
     * @return subPort
     */
    public String getSubport() {
        return subport;
    }

    /**
     * @param subport
     */
    public void setSubport(String subport) {
        this.subport = subport;
    }

    /**
     * @return mainFtpAddr
     */
    public String getMainftpaddr() {
        return mainftpaddr;
    }

    /**
     * @param mainftpaddr
     */
    public void setMainftpaddr(String mainftpaddr) {
        this.mainftpaddr = mainftpaddr;
    }

    /**
     * @return mainFtpDomain
     */
    public String getMainftpdomain() {
        return mainftpdomain;
    }

    /**
     * @param mainftpdomain
     */
    public void setMainftpdomain(String mainftpdomain) {
        this.mainftpdomain = mainftpdomain;
    }

    /**
     * @return mainFtpPort
     */
    public String getMainftpport() {
        return mainftpport;
    }

    /**
     * @param mainftpport
     */
    public void setMainftpport(String mainftpport) {
        this.mainftpport = mainftpport;
    }

    /**
     * @return mainFtpUser
     */
    public String getMainftpuser() {
        return mainftpuser;
    }

    /**
     * @param mainftpuser
     */
    public void setMainftpuser(String mainftpuser) {
        this.mainftpuser = mainftpuser;
    }

    /**
     * @return mainFtpPasswd
     */
    public String getMainftppasswd() {
        return mainftppasswd;
    }

    /**
     * @param mainftppasswd
     */
    public void setMainftppasswd(String mainftppasswd) {
        this.mainftppasswd = mainftppasswd;
    }

    /**
     * @return subFtpAddr
     */
    public String getSubftpaddr() {
        return subftpaddr;
    }

    /**
     * @param subftpaddr
     */
    public void setSubftpaddr(String subftpaddr) {
        this.subftpaddr = subftpaddr;
    }

    /**
     * @return subFtpDomain
     */
    public String getSubftpdomain() {
        return subftpdomain;
    }

    /**
     * @param subftpdomain
     */
    public void setSubftpdomain(String subftpdomain) {
        this.subftpdomain = subftpdomain;
    }

    /**
     * @return subFtpPort
     */
    public String getSubftpport() {
        return subftpport;
    }

    /**
     * @param subftpport
     */
    public void setSubftpport(String subftpport) {
        this.subftpport = subftpport;
    }

    /**
     * @return subFtpUser
     */
    public String getSubftpuser() {
        return subftpuser;
    }

    /**
     * @param subftpuser
     */
    public void setSubftpuser(String subftpuser) {
        this.subftpuser = subftpuser;
    }

    /**
     * @return subFtpPasswd
     */
    public String getSubftppasswd() {
        return subftppasswd;
    }

    /**
     * @param subftppasswd
     */
    public void setSubftppasswd(String subftppasswd) {
        this.subftppasswd = subftppasswd;
    }

    /**
     * @return trackAddr
     */
    public String getTrackaddr() {
        return trackaddr;
    }

    /**
     * @param trackaddr
     */
    public void setTrackaddr(String trackaddr) {
        this.trackaddr = trackaddr;
    }

    /**
     * @return trackPort
     */
    public String getTrackport() {
        return trackport;
    }

    /**
     * @param trackport
     */
    public void setTrackport(String trackport) {
        this.trackport = trackport;
    }

    /**
     * @return satAddr
     */
    public String getSataddr() {
        return sataddr;
    }

    /**
     * @param sataddr
     */
    public void setSataddr(String sataddr) {
        this.sataddr = sataddr;
    }

    /**
     * @return dwnSpeed
     */
    public String getDwnspeed() {
        return dwnspeed;
    }

    /**
     * @param dwnspeed
     */
    public void setDwnspeed(String dwnspeed) {
        this.dwnspeed = dwnspeed;
    }

    /**
     * @return pwrOnOffList
     */
    public String getPwronofflist() {
        return pwronofflist;
    }

    /**
     * @param pwronofflist
     */
    public void setPwronofflist(String pwronofflist) {
        this.pwronofflist = pwronofflist;
    }

    /**
     * @return sec1Start
     */
    public String getSec1start() {
        return sec1start;
    }

    /**
     * @param sec1start
     */
    public void setSec1start(String sec1start) {
        this.sec1start = sec1start;
    }

    /**
     * @return sec2Start
     */
    public String getSec2start() {
        return sec2start;
    }

    /**
     * @param sec2start
     */
    public void setSec2start(String sec2start) {
        this.sec2start = sec2start;
    }

    /**
     * @return sec3Start
     */
    public String getSec3start() {
        return sec3start;
    }

    /**
     * @param sec3start
     */
    public void setSec3start(String sec3start) {
        this.sec3start = sec3start;
    }

    /**
     * @return sec4Start
     */
    public String getSec4start() {
        return sec4start;
    }

    /**
     * @param sec4start
     */
    public void setSec4start(String sec4start) {
        this.sec4start = sec4start;
    }

    /**
     * @return sec5Start
     */
    public String getSec5start() {
        return sec5start;
    }

    /**
     * @param sec5start
     */
    public void setSec5start(String sec5start) {
        this.sec5start = sec5start;
    }

    /**
     * @return sec1End
     */
    public String getSec1end() {
        return sec1end;
    }

    /**
     * @param sec1end
     */
    public void setSec1end(String sec1end) {
        this.sec1end = sec1end;
    }

    /**
     * @return sec2End
     */
    public String getSec2end() {
        return sec2end;
    }

    /**
     * @param sec2end
     */
    public void setSec2end(String sec2end) {
        this.sec2end = sec2end;
    }

    /**
     * @return sec3End
     */
    public String getSec3end() {
        return sec3end;
    }

    /**
     * @param sec3end
     */
    public void setSec3end(String sec3end) {
        this.sec3end = sec3end;
    }

    /**
     * @return sec4End
     */
    public String getSec4end() {
        return sec4end;
    }

    /**
     * @param sec4end
     */
    public void setSec4end(String sec4end) {
        this.sec4end = sec4end;
    }

    /**
     * @return sec5End
     */
    public String getSec5end() {
        return sec5end;
    }

    /**
     * @param sec5end
     */
    public void setSec5end(String sec5end) {
        this.sec5end = sec5end;
    }

    /**
     * @return w1
     */
    public String getW1() {
        return w1;
    }

    /**
     * @param w1
     */
    public void setW1(String w1) {
        this.w1 = w1;
    }

    /**
     * @return w2
     */
    public String getW2() {
        return w2;
    }

    /**
     * @param w2
     */
    public void setW2(String w2) {
        this.w2 = w2;
    }

    /**
     * @return w3
     */
    public String getW3() {
        return w3;
    }

    /**
     * @param w3
     */
    public void setW3(String w3) {
        this.w3 = w3;
    }

    /**
     * @return w4
     */
    public String getW4() {
        return w4;
    }

    /**
     * @param w4
     */
    public void setW4(String w4) {
        this.w4 = w4;
    }

    /**
     * @return w5
     */
    public String getW5() {
        return w5;
    }

    /**
     * @param w5
     */
    public void setW5(String w5) {
        this.w5 = w5;
    }

    /**
     * @return w6
     */
    public String getW6() {
        return w6;
    }

    /**
     * @param w6
     */
    public void setW6(String w6) {
        this.w6 = w6;
    }

    /**
     * @return w7
     */
    public String getW7() {
        return w7;
    }

    /**
     * @param w7
     */
    public void setW7(String w7) {
        this.w7 = w7;
    }

    /**
     * @return d1
     */
    public String getD1() {
        return d1;
    }

    /**
     * @param d1
     */
    public void setD1(String d1) {
        this.d1 = d1;
    }

    /**
     * @return d2
     */
    public String getD2() {
        return d2;
    }

    /**
     * @param d2
     */
    public void setD2(String d2) {
        this.d2 = d2;
    }

    /**
     * @return d3
     */
    public String getD3() {
        return d3;
    }

    /**
     * @param d3
     */
    public void setD3(String d3) {
        this.d3 = d3;
    }

    /**
     * @return d4
     */
    public String getD4() {
        return d4;
    }

    /**
     * @param d4
     */
    public void setD4(String d4) {
        this.d4 = d4;
    }

    /**
     * @return d5
     */
    public String getD5() {
        return d5;
    }

    /**
     * @param d5
     */
    public void setD5(String d5) {
        this.d5 = d5;
    }

    /**
     * @return d6
     */
    public String getD6() {
        return d6;
    }

    /**
     * @param d6
     */
    public void setD6(String d6) {
        this.d6 = d6;
    }

    /**
     * @return d7
     */
    public String getD7() {
        return d7;
    }

    /**
     * @param d7
     */
    public void setD7(String d7) {
        this.d7 = d7;
    }

    /**
     * @return d8
     */
    public String getD8() {
        return d8;
    }

    /**
     * @param d8
     */
    public void setD8(String d8) {
        this.d8 = d8;
    }

    /**
     * @return g1
     */
    public String getG1() {
        return g1;
    }

    /**
     * @param g1
     */
    public void setG1(String g1) {
        this.g1 = g1;
    }

    /**
     * @return g2
     */
    public String getG2() {
        return g2;
    }

    /**
     * @param g2
     */
    public void setG2(String g2) {
        this.g2 = g2;
    }

    /**
     * @return g3
     */
    public String getG3() {
        return g3;
    }

    /**
     * @param g3
     */
    public void setG3(String g3) {
        this.g3 = g3;
    }

    /**
     * @return g4
     */
    public String getG4() {
        return g4;
    }

    /**
     * @param g4
     */
    public void setG4(String g4) {
        this.g4 = g4;
    }

    /**
     * @return g5
     */
    public String getG5() {
        return g5;
    }

    /**
     * @param g5
     */
    public void setG5(String g5) {
        this.g5 = g5;
    }

    /**
     * @return timeMode
     */
    public String getTimemode() {
        return timemode;
    }

    /**
     * @param timemode
     */
    public void setTimemode(String timemode) {
        this.timemode = timemode;
    }

    /**
     * @return downloadTime
     */
    public String getDownloadtime() {
        return downloadtime;
    }

    /**
     * @param downloadtime
     */
    public void setDownloadtime(String downloadtime) {
        this.downloadtime = downloadtime;
    }

    /**
     * @return timeShow
     */
    public String getTimeshow() {
        return timeshow;
    }

    /**
     * @param timeshow
     */
    public void setTimeshow(String timeshow) {
        this.timeshow = timeshow;
    }

    /**
     * @return volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * @param volume
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * @return brightnessBL
     */
    public String getBrightnessbl() {
        return brightnessbl;
    }

    /**
     * @param brightnessbl
     */
    public void setBrightnessbl(String brightnessbl) {
        this.brightnessbl = brightnessbl;
    }

    /**
     * @return brightnessPIC
     */
    public String getBrightnesspic() {
        return brightnesspic;
    }

    /**
     * @param brightnesspic
     */
    public void setBrightnesspic(String brightnesspic) {
        this.brightnesspic = brightnesspic;
    }

    /**
     * @return contrast
     */
    public String getContrast() {
        return contrast;
    }

    /**
     * @param contrast
     */
    public void setContrast(String contrast) {
        this.contrast = contrast;
    }

    /**
     * @return saturation
     */
    public String getSaturation() {
        return saturation;
    }

    /**
     * @param saturation
     */
    public void setSaturation(String saturation) {
        this.saturation = saturation;
    }

    /**
     * @return dayNum
     */
    public String getDaynum() {
        return daynum;
    }

    /**
     * @param daynum
     */
    public void setDaynum(String daynum) {
        this.daynum = daynum;
    }

    /**
     * @return dirDay
     */
    public String getDirday() {
        return dirday;
    }

    /**
     * @param dirday
     */
    public void setDirday(String dirday) {
        this.dirday = dirday;
    }

    /**
     * @return mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return connect_status
     */
    public Byte getConnectStatus() {
        return connectStatus;
    }

    /**
     * @param connectStatus
     */
    public void setConnectStatus(Byte connectStatus) {
        this.connectStatus = connectStatus;
    }

    /**
     * @return connect_time
     */
    public Date getConnectTime() {
        return connectTime;
    }

    /**
     * @param connectTime
     */
    public void setConnectTime(Date connectTime) {
        this.connectTime = connectTime;
    }

    /**
     * @return disconnect_time
     */
    public Date getDisconnectTime() {
        return disconnectTime;
    }

    /**
     * @param disconnectTime
     */
    public void setDisconnectTime(Date disconnectTime) {
        this.disconnectTime = disconnectTime;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return preDwn
     */
    public String getPredwn() {
        return predwn;
    }

    /**
     * @param predwn
     */
    public void setPredwn(String predwn) {
        this.predwn = predwn;
    }

    /**
     * @return p2p
     */
    public String getP2p() {
        return p2p;
    }

    /**
     * @param p2p
     */
    public void setP2p(String p2p) {
        this.p2p = p2p;
    }

    /**
     * @return sync
     */
    public String getSync() {
        return sync;
    }

    /**
     * @param sync
     */
    public void setSync(String sync) {
        this.sync = sync;
    }

    /**
     * @return thirdTask
     */
    public String getThirdtask() {
        return thirdtask;
    }

    /**
     * @param thirdtask
     */
    public void setThirdtask(String thirdtask) {
        this.thirdtask = thirdtask;
    }

    /**
     * @return thirdPath
     */
    public String getThirdpath() {
        return thirdpath;
    }

    /**
     * @param thirdpath
     */
    public void setThirdpath(String thirdpath) {
        this.thirdpath = thirdpath;
    }

    /**
     * @return thirdTimeout
     */
    public String getThirdtimeout() {
        return thirdtimeout;
    }

    /**
     * @param thirdtimeout
     */
    public void setThirdtimeout(String thirdtimeout) {
        this.thirdtimeout = thirdtimeout;
    }

    /**
     * @return thirdVersion
     */
    public String getThirdversion() {
        return thirdversion;
    }

    /**
     * @param thirdversion
     */
    public void setThirdversion(String thirdversion) {
        this.thirdversion = thirdversion;
    }

    /**
     * @return torg_id
     */
    public Integer getTorgId() {
        return torgId;
    }

    /**
     * @param torgId
     */
    public void setTorgId(Integer torgId) {
        this.torgId = torgId;
    }

    /**
     * @return task_id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return default_task_id
     */
    public Long getDefaultTaskId() {
        return defaultTaskId;
    }

    /**
     * @param defaultTaskId
     */
    public void setDefaultTaskId(Long defaultTaskId) {
        this.defaultTaskId = defaultTaskId;
    }

    /**
     * @return tel_enable
     */
    public Short getTelEnable() {
        return telEnable;
    }

    /**
     * @param telEnable
     */
    public void setTelEnable(Short telEnable) {
        this.telEnable = telEnable;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return ad_use_count
     */
    public Integer getAdUseCount() {
        return adUseCount;
    }

    /**
     * @param adUseCount
     */
    public void setAdUseCount(Integer adUseCount) {
        this.adUseCount = adUseCount;
    }

    /**
     * @return ad_count
     */
    public Integer getAdCount() {
        return adCount;
    }

    /**
     * @param adCount
     */
    public void setAdCount(Integer adCount) {
        this.adCount = adCount;
    }

    /**
     * @return rx
     */
    public Long getRx() {
        return rx;
    }

    /**
     * @param rx
     */
    public void setRx(Long rx) {
        this.rx = rx;
    }

    /**
     * @return tx
     */
    public Long getTx() {
        return tx;
    }

    /**
     * @param tx
     */
    public void setTx(Long tx) {
        this.tx = tx;
    }

    /**
     * @return iccid
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * @param iccid
     */
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    /**
     * @return default_task_date
     */
    public Date getDefaultTaskDate() {
        return defaultTaskDate;
    }

    /**
     * @param defaultTaskDate
     */
    public void setDefaultTaskDate(Date defaultTaskDate) {
        this.defaultTaskDate = defaultTaskDate;
    }
}