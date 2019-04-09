package com.seeyoo.visit.model;

import javax.persistence.*;

public class Constant {
    @Id
    private Integer id;

    @Column(name = "vaild_adb")
    private Integer vaildAdb;

    @Column(name = "vailde_bdb")
    private Integer vaildeBdb;

    @Column(name = "vaild_padb")
    private Integer vaildPadb;

    @Column(name = "vaild_pbdb")
    private Integer vaildPbdb;

    @Column(name = "a_level")
    private Integer aLevel;

    @Column(name = "b_level")
    private Integer bLevel;

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
     * @return vaild_adb
     */
    public Integer getVaildAdb() {
        return vaildAdb;
    }

    /**
     * @param vaildAdb
     */
    public void setVaildAdb(Integer vaildAdb) {
        this.vaildAdb = vaildAdb;
    }

    /**
     * @return vailde_bdb
     */
    public Integer getVaildeBdb() {
        return vaildeBdb;
    }

    /**
     * @param vaildeBdb
     */
    public void setVaildeBdb(Integer vaildeBdb) {
        this.vaildeBdb = vaildeBdb;
    }

    /**
     * @return vaild_padb
     */
    public Integer getVaildPadb() {
        return vaildPadb;
    }

    /**
     * @param vaildPadb
     */
    public void setVaildPadb(Integer vaildPadb) {
        this.vaildPadb = vaildPadb;
    }

    /**
     * @return vaild_pbdb
     */
    public Integer getVaildPbdb() {
        return vaildPbdb;
    }

    /**
     * @param vaildPbdb
     */
    public void setVaildPbdb(Integer vaildPbdb) {
        this.vaildPbdb = vaildPbdb;
    }

    /**
     * @return a_level
     */
    public Integer getaLevel() {
        return aLevel;
    }

    /**
     * @param aLevel
     */
    public void setaLevel(Integer aLevel) {
        this.aLevel = aLevel;
    }

    /**
     * @return b_level
     */
    public Integer getbLevel() {
        return bLevel;
    }

    /**
     * @param bLevel
     */
    public void setbLevel(Integer bLevel) {
        this.bLevel = bLevel;
    }
}