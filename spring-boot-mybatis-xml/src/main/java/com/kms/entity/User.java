package com.kms.entity;

public class User {
    private Integer id;

    private String password;

    private Long fullSyncBefore;

    private String name;

    private Long updatedTime;

    private Long createdTime;

    public User() {}

    public User( String name,String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getFullSyncBefore() {
        return fullSyncBefore;
    }

    public void setFullSyncBefore(Long fullSyncBefore) {
        this.fullSyncBefore = fullSyncBefore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}