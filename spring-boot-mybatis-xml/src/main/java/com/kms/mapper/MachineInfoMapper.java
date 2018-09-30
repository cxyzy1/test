package com.kms.mapper;

import com.kms.entity.MachineInfo;

public interface MachineInfoMapper {
    int add(MachineInfo record);

    MachineInfo get(String mac);

}