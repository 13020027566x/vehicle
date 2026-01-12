package com.finhub.framework.common.lock;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;

/**
 * @author : liuwei
 * @date : 2021/11/4
 * @desc :
 */
@Slf4j
@UtilityClass
public class DistributeLockHolder {

    private static final NamedThreadLocal<String> DISTRIBUTE_LOCK_KEY_LOCAL =
        new NamedThreadLocal<>("DISTRIBUTE_LOCK_KEY");

    /**
     * set redis lock key or keys append
     *
     * @param key redis final key or keys append
     */
    public static void put(String key) {
        DISTRIBUTE_LOCK_KEY_LOCAL.set(key);
    }

    /**
     * get redis lock key or keys append
     */
    public static String get() {
        return DISTRIBUTE_LOCK_KEY_LOCAL.get();
    }

    /**
     * remove threadLocal map
     */
    public static void remove() {
        DISTRIBUTE_LOCK_KEY_LOCAL.remove();
    }

}
