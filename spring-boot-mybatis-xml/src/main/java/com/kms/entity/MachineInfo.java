package com.kms.entity;

import java.util.Date;

public class MachineInfo {
    private Short id;

    private Short dataCenter;

    private String mac;

    private Date createdTime;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(Short dataCenter) {
        this.dataCenter = dataCenter;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}