package com.kms.mapper;

import com.kms.entity.ExpungedObj;

public interface ExpungedObjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpungedObj record);

    int insertSelective(ExpungedObj record);

    ExpungedObj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpungedObj record);

    int updateByPrimaryKey(ExpungedObj record);
}