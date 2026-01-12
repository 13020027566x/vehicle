package com.finhub.framework.core.date;

import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@UtilityClass
public class DateConstants {

    /**
     * 毫秒
     */
    public static final long MS = 1;
    /**
     * 每秒钟的毫秒数
     */
    public static final long SECOND_MS = MS * 1000;
    /**
     * 每分钟的毫秒数
     */
    public static final long MINUTE_MS = SECOND_MS * 60;
    /**
     * 每小时的毫秒数
     */
    public static final long HOUR_MS = MINUTE_MS * 60;
    /**
     * 每天的毫秒数
     */
    public static final long DAY_MS = HOUR_MS * 24;

    public static final String GMT_8 = "GMT+8";

    public static final String NORM_YEAR_PATTERN = "yyyy";

    public static final DateFormat NORM_YEAR_FORMAT = new SimpleDateFormat(NORM_YEAR_PATTERN);

    public static final String NORM_YYYYMMDD_PATTERN = "yyyyMMdd";

    public static final DateFormat NORM_YYYYMMDD_FORMAT = new SimpleDateFormat(NORM_YYYYMMDD_PATTERN);

    public static final String NORM_YYYYMMDDHHMMSS_PATTERN = "yyyyMMddHHmmss";

    public static final DateFormat NORM_YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat(NORM_YYYYMMDDHHMMSS_PATTERN);

    /**
     * 标准日期格式
     */
    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";

    public static final DateFormat NORM_DATE_FORMAT = new SimpleDateFormat(NORM_DATE_PATTERN);

    /**
     * 标准时间格式
     */
    public static final String NORM_TIME_PATTERN = "HH:mm:ss";

    public static final DateFormat NORM_TIME_FORMAT = new SimpleDateFormat(NORM_TIME_PATTERN);

    /**
     * 标准日期时间格式，精确到分
     */
    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";

    public static final DateFormat NORM_DATETIME_MINUTE_FORMAT = new SimpleDateFormat(NORM_DATETIME_MINUTE_PATTERN);

    /**
     * 标准日期时间格式，精确到秒
     */
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final DateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat(NORM_DATETIME_PATTERN);

    /**
     * 标准日期时间格式，精确到毫秒
     */
    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final DateFormat NORM_DATETIME_MS_FORMAT = new SimpleDateFormat(NORM_DATETIME_MS_PATTERN);

    /**
     * HTTP头中日期时间格式
     */
    public static final String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    public static final DateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat(HTTP_DATETIME_PATTERN);

    public static final char Y_CHAR = 'y';
    public static final char D_CHAR = 'd';
    public static final char H_CHAR = 'h';
    public static final char M_CHAR = 'm';
    public static final char S_CHAR = 's';
    public static final int NUM_2 = 2;

    static {
        TimeZone china = TimeZone.getTimeZone(GMT_8);
        TimeZone.setDefault(china);

        NORM_YEAR_FORMAT.setTimeZone(china);
        NORM_YYYYMMDD_FORMAT.setTimeZone(china);
        NORM_YYYYMMDDHHMMSS_FORMAT.setTimeZone(china);
        NORM_DATE_FORMAT.setTimeZone(china);
        NORM_TIME_FORMAT.setTimeZone(china);
        NORM_DATETIME_MINUTE_FORMAT.setTimeZone(china);
        NORM_DATETIME_FORMAT.setTimeZone(china);
        NORM_DATETIME_MS_FORMAT.setTimeZone(china);
        HTTP_DATETIME_FORMAT.setTimeZone(china);
    }
}
