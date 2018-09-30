package com.kms.mapper;

import com.kms.entity.ObjTag;

public interface ObjTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ObjTag record);

    int insertSelective(ObjTag record);

    ObjTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ObjTag record);

    int updateByPrimaryKey(ObjTag record);
}