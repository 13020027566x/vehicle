package com.finhub.framework.safety.kms;

import com.finhub.framework.safety.property.SafetyProperties;

import cn.hutool.json.JSONUtil;
import com.aliyun.kms20160120.Client;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云 KMS 客户端
 *
 * @author zhenxing_liang
 * @version 1.0
 * @since 2023/02/13 11:37
 */
@Slf4j
@UtilityClass
public class KmsClient {

    private static final Map<String, Method> KMS_V20_CLIENT_METHOD_MAP = new HashMap<>();

    static {
        Class<Client> clazz = Client.class;
        Method[] clazzMethods = clazz.getMethods();
        for (Method method : clazzMethods) {
            KMS_V20_CLIENT_METHOD_MAP.put(method.getName(), method);
        }
    }

    private static volatile Client KMS_V20_CLIENT;

    /**
     * 获取 KMS 客户端 For V2.0 SDK
     * @return
     */
    public static Client me4v20() {
        if (KMS_V20_CLIENT == null) {
            synchronized (Client.class) {
                if (KMS_V20_CLIENT == null) {
                    log.info("KMS_V20_CLIENT 初始化开始...");
                    Config config = new Config();
                    config.setCredential(new com.aliyun.credentials.Client());
                    config.setRegionId(SafetyProperties.me().getKmsRegionId());
                    try {
                        KMS_V20_CLIENT = new Client(config);
                        log.info("KMS_V20_CLIENT 初始化完成.");
                    } catch (Exception e) {
                        log.error("KMS_V20_CLIENT 初始化失败.", e);
                    }
                }
            }
        }
        return KMS_V20_CLIENT;
    }

    public static <T, R> T execute4v20(String methodName, R request) {
        methodName = methodName + "WithOptions";
        Method method = KMS_V20_CLIENT_METHOD_MAP.get(methodName);
        if (method == null) {
            log.error("KMS_V20_CLIENT_METHOD_MAP 中不存在此方法. [method={}]", methodName);
            return null;
        }

        T response = null;
        RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions().setIgnoreSSL(false);
        try {
            response = (T) method.invoke(me4v20(), request, runtime);
            log.debug("KMS_V20_CLIENT 执行方法成功. [method={}, request={}, runtime={}, response={}]", methodName,
                JSONUtil.toJsonStr(request), JSONUtil.toJsonStr(runtime), JSONUtil.toJsonStr(response));
        } catch (Exception e) {
            log.error("KMS_V20_CLIENT 执行方法失败. [method={}, request={}, runtime={}]", methodName, JSONUtil.toJsonStr(request), JSONUtil.toJsonStr(runtime), e);
        }
        return response;
    }

    /**
     * 获取 KMS 客户端 For V1.0 SDK
     * @return
     */
    public static void me4v10() {

    }

    public static <T, R> T execute4v10(String methodName, R request) {
        return null;
    }
}
