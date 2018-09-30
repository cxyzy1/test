package com.kms.utils;

import org.junit.Assert;
import org.junit.Test;

import java.net.SocketException;
import java.net.UnknownHostException;

public class DeviceUtilTest {
    @Test
    public void test() throws SocketException, UnknownHostException {
        Assert.assertNotNull(DeviceUtil.getLocalMac());
    }
}
