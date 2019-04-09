package com.seeyoo.visit.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "visit_camera")
@Data
public class VisitCamera {

    @Id
    private Long id;

    private Integer age;

    private Short gender;

    private Integer beauty;

    private Integer stay;

    private Date stamp;

    private Date time;

    @Column(name = "assets_id")
    private Integer assetsId;

    @Column(name = "visit_id")
    private Long visitId;


}