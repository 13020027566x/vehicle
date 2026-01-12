package com.finhub.framework.swift.utils;

import com.finhub.framework.core.SpringUtils;
import com.finhub.framework.swift.property.ItfConfig;
import com.finhub.framework.swift.property.impl.ItfConfigImpl;

public class ItfConfigUtils {

    private volatile static ItfConfig cfg;

    public static ItfConfig getInstance(){
        if (cfg == null) {
            synchronized (ItfConfigUtils.class) {
                if (cfg == null) {
                    cfg = new ItfConfigImpl(SpringUtils.getApplicationContext().getEnvironment());
                }
            }
        }
        return cfg;
    }

}
