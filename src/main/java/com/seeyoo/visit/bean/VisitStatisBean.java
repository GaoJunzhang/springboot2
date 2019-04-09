package com.seeyoo.visit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class VisitStatisBean {
    private Date visitDate;
    private BigInteger visitCount;
    public VisitStatisBean(Date visitDate, BigInteger visitCount) {
        this.visitDate = visitDate;
        this.visitCount = visitCount;
    }

    public VisitStatisBean() {

    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
