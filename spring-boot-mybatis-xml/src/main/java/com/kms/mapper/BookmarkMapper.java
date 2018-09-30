package com.kms.mapper;

import com.kms.entity.Bookmark;

public interface BookmarkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bookmark record);

    int insertSelective(Bookmark record);

    Bookmark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bookmark record);

    int updateByPrimaryKey(Bookmark record);
}