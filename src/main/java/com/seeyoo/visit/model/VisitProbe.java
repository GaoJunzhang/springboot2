package com.seeyoo.visit.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

@Table(name = "visit_probe")
@Getter
@Setter
public class VisitProbe {
    @Id
    private Long id;

    private String mac;

    private Integer db;

    @Column(name = "begin_time")
    private Timestamp beginTime;

    @Column(name = "assets_id")
    private Integer assetsId;

    @Column(name = "end_time")
    private Timestamp endTime;

    private Timestamp time;

    @Column(name = "is_old")
    private Short isOld;
}