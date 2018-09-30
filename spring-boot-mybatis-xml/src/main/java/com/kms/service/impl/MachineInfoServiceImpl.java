package com.kms.service.impl;

import com.kms.entity.MachineInfo;
import com.kms.mapper.MachineInfoMapper;
import com.kms.service.MachineInfoService;
import com.kms.utils.DeviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineInfoServiceImpl implements MachineInfoService {

    //机器编号信息：数组第一个是数据中心编码（默认是0），第二个是机器编号
    private long[] machineIdArr = null;

    @Autowired
    private MachineInfoMapper machineInfoMapper;

    /**
     * 启动之后初始化machineIdArr,方便后续其他程序从缓存变量中获取
     *
     * @throws Exception
     */
    @Override
    public void initMachineId() throws Exception {
        queryMachineId();
    }

    @Override
    public long[] getMachineId() {
        return machineIdArr;
    }

    /**
     * 查询数据库获取机器编号和数据中心编号
     * 对于机器，采用mac地址作为唯一标识，如果不存在，则新增记录进去。
     */
    public long[] queryMachineId() throws Exception {
        machineIdArr = new long[2];
        String mac = DeviceUtil.getLocalMac();
        MachineInfo machineInfo = get(mac);
        if (machineInfo == null) {
            add(mac);
            machineInfo = get(mac);
        }
        machineIdArr[0] = machineInfo.getDataCenter();
        machineIdArr[1] = machineInfo.getId();
        return machineIdArr;
    }

    public MachineInfo get(String mac) {
        MachineInfo machineInfo = machineInfoMapper.get(mac);
        return machineInfo;
    }

    public void add(String mac) {
        //默认数据中心编号
        final Short DEFAULT_DATA_CENTER = 0;
        MachineInfo machineInfo = new MachineInfo();
        machineInfo.setMac(mac);
        machineInfo.setDataCenter(DEFAULT_DATA_CENTER);
        machineInfoMapper.add(machineInfo);
    }

}
