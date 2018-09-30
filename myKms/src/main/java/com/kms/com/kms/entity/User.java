package com.kms.com.kms.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author beibei
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    @Id private String id;
    private String password;
    private Long fullSyncBefore;
    private String name;
    private Long updatedTime;
    private Long createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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