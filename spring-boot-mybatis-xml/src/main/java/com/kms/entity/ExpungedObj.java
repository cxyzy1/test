package com.kms.entity;

import java.util.Date;

public class ExpungedObj {
    private Integer id;

    private Date createTime;

    private String expungedObjcol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExpungedObjcol() {
        return expungedObjcol;
    }

    public void setExpungedObjcol(String expungedObjcol) {
        this.expungedObjcol = expungedObjcol;
    }
}