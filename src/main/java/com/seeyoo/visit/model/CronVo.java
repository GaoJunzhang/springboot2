package com.seeyoo.visit.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cron_vo")
public class CronVo implements Serializable {
    private static final long serialVersionUID = 4829802760047207681L;
    @Id
    @Column(name = "cron_id")
    private Integer cronId;

    private String cron;

    @Column(name = "quartz_name")
    private String quartzName;

    private Short status;

    @Column(name = "scheduler_class")
    private String schedulerClass;

    private Date ts;

    public CronVo() {
    }

    /**
     * @return cron_id
     */
    public Integer getCronId() {
        return cronId;
    }

    /**
     * @param cronId
     */
    public void setCronId(Integer cronId) {
        this.cronId = cronId;
    }

    /**
     * @return cron
     */
    public String getCron() {
        return cron;
    }

    /**
     * @param cron
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * @return quartz_name
     */
    public String getQuartzName() {
        return quartzName;
    }

    /**
     * @param quartzName
     */
    public void setQuartzName(String quartzName) {
        this.quartzName = quartzName;
    }

    /**
     * @return status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return scheduler_class
     */
    public String getSchedulerClass() {
        return schedulerClass;
    }

    /**
     * @param schedulerClass
     */
    public void setSchedulerClass(String schedulerClass) {
        this.schedulerClass = schedulerClass;
    }

    /**
     * @return ts
     */
    public Date getTs() {
        return ts;
    }

    /**
     * @param ts
     */
    public void setTs(Date ts) {
        this.ts = ts;
    }
}