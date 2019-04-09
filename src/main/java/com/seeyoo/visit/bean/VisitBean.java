package com.seeyoo.visit.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class VisitBean {
    private Timestamp start;
    private Timestamp end;
    private Integer assetsId;
    private String time;
    private int sdb;
    private int edb;

}
