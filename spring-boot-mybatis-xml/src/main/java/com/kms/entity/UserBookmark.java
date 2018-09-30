package com.kms.entity;

public class UserBookmark {
    private Integer id;

    private Integer userId;

    private Long bookmarkId;

    private Byte isPrivate;

    private String bookmarkUrl;

    private String bookmarkTitle;

    private String tagNames;

    private String state;

    private Long updatedTime;

    private Long createdTime;

    private Short progress;

    private Long progressTime;

    public UserBookmark() {
    }

    public UserBookmark(Integer userId, String bookmarkUrl, String bookmarkTitle, String tagNames) {
        this.userId = userId;
        this.bookmarkUrl = bookmarkUrl;
        this.bookmarkTitle = bookmarkTitle;
        this.tagNames = tagNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Byte getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Byte isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getBookmarkUrl() {
        return bookmarkUrl;
    }

    public void setBookmarkUrl(String bookmarkUrl) {
        this.bookmarkUrl = bookmarkUrl;
    }

    public String getBookmarkTitle() {
        return bookmarkTitle;
    }

    public void setBookmarkTitle(String bookmarkTitle) {
        this.bookmarkTitle = bookmarkTitle;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Short getProgress() {
        return progress;
    }

    public void setProgress(Short progress) {
        this.progress = progress;
    }

    public Long getProgressTime() {
        return progressTime;
    }

    public void setProgressTime(Long progressTime) {
        this.progressTime = progressTime;
    }
}