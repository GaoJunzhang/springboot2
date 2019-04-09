package com.seeyoo.visit.model;

import java.util.Date;
import javax.persistence.*;

public class Calendar {
    private Date datelist;

    private Long id;

    /**
     * @return datelist
     */
    public Date getDatelist() {
        return datelist;
    }

    /**
     * @param datelist
     */
    public void setDatelist(Date datelist) {
        this.datelist = datelist;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}