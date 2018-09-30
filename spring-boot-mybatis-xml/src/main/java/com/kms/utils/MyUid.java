package com.kms.utils;

public class MyUid {
    private static SnowFlake snowFlake;

    public static void init(long[] machineIdArr)
    {
        snowFlake = new SnowFlake(machineIdArr[0], machineIdArr[1]);
    }

    /**
     * 获取全局唯一ID，用于作为书签等的唯一标识。
     * @return
     */
    public static long getUid()
    {
        return snowFlake.nextId();
    }
}
