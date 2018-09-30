package com.kms.appRunners;

import com.kms.service.MachineInfoService;
import com.kms.utils.MyUid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MachineIdApplicationRunner implements ApplicationRunner {

    @Autowired
    private MachineInfoService machineInfoService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        machineInfoService.initMachineId();
        MyUid.init(machineInfoService.getMachineId());
    }

}