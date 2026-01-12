package com.finhub.framework.logback.constant;

import lombok.experimental.UtilityClass;

/**
 * @author Mickey
 * @date 2020/6/3
 * @since 1.2
 */
@UtilityClass
public final class LogConstants {

    public static final int RANDOM_REQUEST_ID_LENGTH = 16;

    public static final String TIME_ZONE = "Asia/Shanghai";

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String UNKNOWN_VALUE = "-";

    public static final String S_COMMA = ",";

    public static final String S_SPACE = " ";

    public static final String S_COLON = ":";

    public static final String S_ENTER = "\n";

    public static final String REQ_LEVEL = "req.level";

    public static final String S_EMPTY_JSON = "{}";

    public static final String S_NULL = "null";

    public static final String S_EMPTY = "";

    public static final char C_BRACKET_START = '[';

    public static final char C_BRACKET_END = ']';

    public static final String S_EMPTY_ARRAY = "[]";

    public static final String S_LAMBDA_TYPE = "$Lambda$";

    public static final char C_COMMA = ',';

    public static final char C_SPACE = ' ';

    public static final String S_PRINT_FINHUB_PACKAGE_PREFIX = "com.finhub";

    public static final String S_PRINT_FBT_PACKAGE_PREFIX = "com.fenbeitong";

    public static final String S_SERVLET_REQUEST_PARAMETER = "ServletRequest";

    public static final String S_SERVLET_RESPONSE_PARAMETER = "ServletResponse";

    public static final String LOG_CLASS_METHOD_TEMPLATE = "%s.%s(...)";

    //-------------------------------------- header key ------------------------------------------------------

    public static final String HEADER_TOKEN_KEY = "X-Auth-Token";

    public static final String HEADER_FROM_IP_KEY = "X-From-Ip";

    public static final String HEADER_FROM_APP_KEY = "X-From-App";

    public static final String HEADER_FROM_DEPT_KEY = "X-From-Dept";

    public static final String HEADER_TRACE_ID_KEY = "X-Trace-Id";

    public static final String HEADER_REQUEST_ID_KEY = "X-Request-Id";

    public static final String HEADER_FROM_VEHICLE_KEY = "X-From-Vehicle";

    //-------------------------------------- 00 common response-----------------------------------------------

    public static final String MDC_KEY_FROM_IP = "from_ip";

    public static final String MDC_KEY_FROM_PORT = "from_port";

    public static final String MDC_KEY_FROM_APP = "from_app";

    public static final String MDC_KEY_FROM_DEPT = "from_dept";

    public static final String MDC_KEY_FROM_LANG = "from_lang";

    public static final String MDC_KEY_FROM_VEHICLE = "from_vehicle";

    public static final String MDC_KEY_TO_IP = "to_ip";

    public static final String MDC_KEY_TO_PORT = "to_port";

    public static final String MDC_KEY_TO_APP = "to_app";

    public static final String MDC_KEY_TO_DEPT = "to_dept";

    public static final String MDC_KEY_TO_LANG = "to_lang";

    public static final String MDC_KEY_TO_VEHICLE = "to_vehicle";

    public static final String MDC_KEY_VEHICLE_VERSION = "vehicle_version";

    /**
     * sky-walking-id
     */
    public static final String MDC_KEY_TRACE_ID = "trace_id";

    /**
     * sky-walking 下级ID，web 异步响应id，由于mvc异步逻辑会发起两次请求，第二次为异步响应时模拟请求，导致出现两个trace_id,无法合并。
     */
    public static final String MDC_KEY_LOWER_TRACE_ID = "lower_trace_id";

    /**
     * 日志标记 请求id
     */
    public static final String MDC_KEY_CAMEL_REQUEST_ID = "requestId";

    /**
     * 日志标记 请求id
     */
    public static final String MDC_KEY_REQUEST_ID = "request_id";

    /**
     * 日志标记 耗时
     */
    public static final String MDC_KEY_COST = "_cost_";

    /**
     * 日志输出时间
     */
    public static final String MDC_LOG_TIME = "_time_";

    /**
     * 日志级别
     */
    public static final String MDC_LOG_LEVEL = "_level_";

    /**
     * 日志输出位置
     */
    public static final String MDC_LOG_TARGET_CLASS = "_location_";

    /**
     * 日志输出内容
     */
    public static final String MDC_LOG_MESSAGE = "_message_";

    /**
     * 日志输出异常
     */
    public static final String MDC_LOG_THROWABLE = "_throwable_";

    /**
     * 方法执行线程
     */
    public static final String MDC_LOG_THREAD = "thread";


    //-------------------------------------- 10 dubbo request-----------------------------------------------

    public static final String MDC_KEY_REMOTE_METHOD = "remote_method";

    public static final String MDC_KEY_REMOTE_PARAMETER = "remote_parameter";

    public static final String MDC_KEY_REMOTE_ATTACHMENT = "remote_attachment";


    //-------------------------------------- 10 dubbo response-----------------------------------------------

    public static final String MDC_KEY_REMOTE_SUCCESS = "remote_success";

    public static final String MDC_KEY_REMOTE_RESULT = "remote_result";

    //--------------------------------------web request-----------------------------------------------

    /**
     * 日志标记 请求ip
     */
    public static final String MDC_KEY_REQUEST_IP = "request_ip";
    /**
     * 日志标记 请求路由
     */
    public static final String MDC_KEY_REQUEST_URI = "request_uri";

    /**
     * 日志标记 请求方式
     */
    public static final String MDC_KEY_REQUEST_METHOD = "request_method";

    /**
     * 日志标记 请求体
     */
    public static final String MDC_KEY_REQUEST_BODY = "request_body";

    /**
     * 日志标记 请求体
     */
    public static final String MDC_KEY_REQUEST_PARAMETER = "request_parameter";

    /**
     * 日志标记 请求头
     */
    public static final String MDC_KEY_REQUEST_HEADER = "request_header";
    /**
     * 日志标记 请求头
     */
    public static final String MDC_KEY_REQUEST_ATTRIBUTE = "request_attribute";

    //--------------------------------------30 web response-----------------------------------------------

    /**
     * 日志标记 返回状态
     */
    public static final String MDC_KEY_RESPONSE_SUCCESS = "response_success";

    /**
     * 日志标记 返回状态
     */
    public static final String MDC_KEY_RESPONSE_STATUS = "response_status";

    /**
     * 日志标记 返回头
     */
    public static final String MDC_KEY_RESPONSE_HEADER = "response_header";

    /**
     * 日志标记 返回体
     */
    public static final String MDC_KEY_RESPONSE_BODY = "response_result";

    //--------------------------------------40 aspectj -----------------------------------------------

    public static final String MDC_KEY_METHOD = "handle_method";

    public static final String MDC_KEY_PARAMETER = "handle_parameter";

    public static final String MDC_KEY_SUCCESS = "handle_success";

    public static final String MDC_KEY_RESULT = "handle_result";

    //--------------------------------------50 other ------------------------------------------------
    public static final String SPRING_APPLICATION_DEPT_PROPERTY = "spring.application.dept";

    public static final String SPRING_APPLICATION_NAME_PROPERTY = "spring.application.name";

    public static final String SPRING_SKYWALKING_SERVICE_NAME_PROPERTY = "skywalking.agent.service_name";

    public static final String SPRING_VEHICLE_VERSION_PROPERTY = "vehicle.version";

    public static final String WEB_SERVER_PORT_PROPERTY = "server.port";

    public static final String RPC_SERVER_PORT_PROPERTY = "dubbo.protocol.port";

    public static final String DEFAULT_PORT = "8080";

}
