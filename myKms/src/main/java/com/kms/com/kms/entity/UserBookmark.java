package com.kms.com.kms.entity;

import lombok.*;
import org.bson.BsonTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author beibei
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserBookmark {
    @Id private String id;
    private String userId;
    private Byte isPrivate;
    private String url;
    private String title;
    private String tagNames;
    private String state;

    public static String FIELD_UPDATED_TIME="updatedTime";
    private Date updatedTime;

    public static String FIELD_CREATED_TIME="createdTime";
    private Date createdTime;

    public static String FIELD_PROGRESS="progress";
    private short progress;

    public static String FIELD_PROGRESS_TIME="progressTime";
    private Date progressTime;
}