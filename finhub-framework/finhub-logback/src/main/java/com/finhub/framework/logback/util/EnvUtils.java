package com.finhub.framework.logback.util;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 * @author : liuwei
 * @date : 2021/12/15
 * @desc :
 */
@UtilityClass
public final class EnvUtils {
    /**
     * 生产环境
     */
    public static final String ENV_PROD = "prod";

    /**
     * 生产环境
     */
    public static final String ENV_PROD_B = "prod-b";

    /**
     * 生产环境
     */
    public static final String ENV_PRO = "pro";

    /**
     * 生产环境
     */
    public static final String ENV_PRO_B = "pro-b";

    /**
     * 预发布环境
     */
    public static final String ENV_UAT = "uat";

    /**
     * 测试环境
     */
    public static final String ENV_FAT = "fat";

    /**
     * 测试环境
     */
    public static final String ENV_FAT_B = "fat-b";

    /**
     * 开发环境
     */
    public static final String ENV_DEV = "dev";

    /**
     * 判断是否为生产环境
     *
     * @return
     */
    public static boolean isProd(String profile) {
        return !StrUtil.isBlank(profile) && (profile.endsWith(ENV_PROD) || profile.endsWith(ENV_PRO) || profile.endsWith(ENV_PROD_B) || profile.endsWith(ENV_PRO_B));
    }

    /**
     * 判断是否为生产环境
     *
     * @return
     */
    public static boolean isNotProd(String profile) {
        return StrUtil.isBlank(profile) || (!profile.endsWith(ENV_PROD) && !profile.endsWith(ENV_PRO) && !profile.endsWith(ENV_PROD_B) && !profile.endsWith(ENV_PRO_B));
    }

    /**
     * 判断是否为 UAT
     *
     * @return
     */
    public static boolean isUat(String profile) {
        return !StrUtil.isBlank(profile) && profile.endsWith(ENV_UAT);
    }

    /**
     * 判断是否为 UAT
     *
     * @return
     */
    public static boolean isNotUat(String profile) {
        return StrUtil.isBlank(profile) || !profile.endsWith(ENV_UAT);
    }

    /**
     * 判断是否为 DEV
     *
     * @return
     */
    public static boolean isDev(String profile) {
        return !StrUtil.isBlank(profile) && profile.endsWith(ENV_DEV);
    }

    /**
     * 判断是否为 DEV
     *
     * @return
     */
    public static boolean isNotDev(String profile) {
        return StrUtil.isBlank(profile) || !profile.endsWith(ENV_DEV);
    }

    /**
     * 判断是否为 FAT
     *
     * @return
     */
    public static boolean isFat(String profile) {
        return !StrUtil.isBlank(profile) && (profile.endsWith(ENV_FAT) || profile.endsWith(ENV_FAT_B));
    }

    /**
     * 判断是否为 FAT
     *
     * @return
     */
    public static boolean isNotFat(String profile) {
        return StrUtil.isBlank(profile) || (!profile.endsWith(ENV_FAT) && !profile.endsWith(ENV_FAT_B));
    }

}
