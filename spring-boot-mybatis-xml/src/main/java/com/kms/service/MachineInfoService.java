package com.kms.service;

public interface MachineInfoService {
    void initMachineId() throws Exception;
    /**
     * 查询数据库获取机器编号和数据中心编号(此字段暂时默认是0)
     */
    long[] getMachineId();
}
