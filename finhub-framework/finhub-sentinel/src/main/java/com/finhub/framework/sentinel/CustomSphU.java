package com.finhub.framework.sentinel;


import com.finhub.framework.sentinel.constants.SentinelConstants;
import com.finhub.framework.sentinel.property.SentinelProperties;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.Env;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * Sentinel 功能注册器（代码块熔断限流使用）
 *
 * @author zhenxing_liang
 */
public class CustomSphU {

    private static final Object[] OBJECTS0 = new Object[0];

    private CustomSphU() {
    }

    private static String getResourceName(String name) {
        return SentinelProperties.me().getProfile() + SentinelConstants.RESOURCE_NAME_CONCAT_CHAR + name;
    }

    public static Entry entry(String name) throws BlockException {
        return Env.sph.entry(getResourceName(name), EntryType.OUT, 1, OBJECTS0);
    }

    public static Entry entry(String name, int batchCount) throws BlockException {
        return Env.sph.entry(getResourceName(name), EntryType.OUT, batchCount, OBJECTS0);
    }

    public static Entry entry(String name, EntryType trafficType) throws BlockException {
        return Env.sph.entry(getResourceName(name), trafficType, 1, OBJECTS0);
    }

    public static Entry entry(String name, EntryType trafficType, int batchCount) throws BlockException {
        return Env.sph.entry(getResourceName(name), trafficType, batchCount, OBJECTS0);
    }

    public static Entry entry(String name, EntryType trafficType, int batchCount, Object... args)
        throws BlockException {
        return Env.sph.entry(getResourceName(name), trafficType, batchCount, args);
    }

    public static AsyncEntry asyncEntry(String name) throws BlockException {
        return Env.sph.asyncEntry(getResourceName(name), EntryType.OUT, 1, OBJECTS0);
    }

    public static AsyncEntry asyncEntry(String name, EntryType trafficType) throws BlockException {
        return Env.sph.asyncEntry(getResourceName(name), trafficType, 1, OBJECTS0);
    }

    public static AsyncEntry asyncEntry(String name, EntryType trafficType, int batchCount, Object... args)
        throws BlockException {
        return Env.sph.asyncEntry(getResourceName(name), trafficType, batchCount, args);
    }

    public static Entry entryWithPriority(String name) throws BlockException {
        return Env.sph.entryWithPriority(getResourceName(name), EntryType.OUT, 1, true);
    }

    public static Entry entryWithPriority(String name, EntryType trafficType) throws BlockException {
        return Env.sph.entryWithPriority(getResourceName(name), trafficType, 1, true);
    }

    public static Entry entry(String name, int resourceType, EntryType trafficType) throws BlockException {
        return Env.sph.entryWithType(getResourceName(name), resourceType, trafficType, 1, OBJECTS0);
    }

    public static Entry entry(String name, int resourceType, EntryType trafficType, Object[] args)
        throws BlockException {
        return Env.sph.entryWithType(getResourceName(name), resourceType, trafficType, 1, args);
    }

    public static AsyncEntry asyncEntry(String name, int resourceType, EntryType trafficType) throws BlockException {
        return Env.sph.asyncEntryWithType(getResourceName(name), resourceType, trafficType, 1, false, OBJECTS0);
    }

    public static AsyncEntry asyncEntry(String name, int resourceType, EntryType trafficType, Object[] args)
        throws BlockException {
        return Env.sph.asyncEntryWithType(getResourceName(name), resourceType, trafficType, 1, false, args);
    }

    public static AsyncEntry asyncEntry(String name, int resourceType, EntryType trafficType, int batchCount,
        Object[] args) throws BlockException {
        return Env.sph.asyncEntryWithType(getResourceName(name), resourceType, trafficType, batchCount, false, args);
    }
}
