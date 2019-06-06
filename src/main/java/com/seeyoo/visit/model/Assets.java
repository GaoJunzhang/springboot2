package com.seeyoo.visit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
public class Assets {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String mac;

    private Timestamp time;

    private String name;

    private Short type;

    @Column(name = "tgroup_id")
    private Long tgroupId;

    @Column(name = "tgroup_code")
    private String tgroupCode;

    @Column(name = "terminal_name")
    private String terminalName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}