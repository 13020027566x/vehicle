package com.finhub.framework.agent.transformer;

import com.google.common.collect.Lists;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.List;

/**
 * 自定义 ClassFileTransformer
 *
 * @author taomingkai
 * @version 1.0.0
 * @since 2020/7/30 11:30 上午
 */
@Slf4j
public class CustomClassFileTransformer implements ClassFileTransformer {

    /**
     * <groupId>org.apache.httpcomponents</groupId>
     * <artifactId>httpclient</artifactId>
     * <version>4.5.13</version>
     */
    public static final String CLOSEABLE_HTTP_CLIENT_CLASS_NAME = "org.apache.http.impl.client.CloseableHttpClient";

    /**
     * <groupId>org.apache.httpcomponents</groupId>
     * <artifactId>httpasyncclient</artifactId>
     * <version>4.1.4</version>
     */
    public static final String CLOSEABLE_HTTP_ASYNC_CLIENT_CLASS_NAME =
        "org.apache.http.impl.nio.client.CloseableHttpAsyncClient";

    /**
     * <groupId>cn.hutool</groupId>
     * <artifactId>hutool-all</artifactId>
     * <version>5.6.5</version>
     */
    public static final String HUTOOL_HTTP_REQUEST_CLASS_NAME = "cn.hutool.http.HttpRequest";

    /**
     * <groupId>com.squareup.okhttp3</groupId>
     * <artifactId>okhttp</artifactId>
     * <version>3.14.9</version>
     */
    public static final String OK_HTTP_CLIENT_CLASS_NAME = "okhttp3.OkHttpClient";

    private static final String HTTP_HOST_CLASS_NAME = "org.apache.http.HttpHost";

    private static final String HTTP_REQUEST_CLASS_NAME = "org.apache.http.HttpRequest";

    private static final String HTTP_URI_REQUEST_CLASS_NAME = "org.apache.http.client.methods.HttpUriRequest";

    private static final String HTTP_CONTEXT_CLASS_NAME = "org.apache.http.protocol.HttpContext";

    private static final String RESPONSE_HANDLER_CLASS_NAME = "org.apache.http.client.ResponseHandler";

    private static final String HTTP_ASYNC_REQUEST_PRODUCER_CLASS_NAME =
        "org.apache.http.nio.protocol.HttpAsyncRequestProducer";

    private static final String HTTP_ASYNC_REQUEST_CONSUMER_CLASS_NAME =
        "org.apache.http.nio.protocol.HttpAsyncResponseConsumer";

    private static final String FUTURE_CALL_BACK_CLASS_NAME = "org.apache.http.concurrent.FutureCallback";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
        ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        if (CLOSEABLE_HTTP_CLIENT_CLASS_NAME.replaceAll("\\.", "/").equals(className)) {
            return transformClassForCloseableHttpClient(className);
        }

        if (CLOSEABLE_HTTP_ASYNC_CLIENT_CLASS_NAME.replaceAll("\\.", "/").equals(className)) {
            return transformClassForCloseableHttpAsyncClient(className);
        }

        if (HUTOOL_HTTP_REQUEST_CLASS_NAME.replaceAll("\\.", "/").equals(className)) {
            return transformClassForHutoolHttpRequest(className);
        }

        if (OK_HTTP_CLIENT_CLASS_NAME.replaceAll("\\.", "/").equals(className)) {
            return transformClassForOkHttpClient(className);
        }

        return null;
    }

    private byte[] transformClassForOkHttpClient(String className) {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass okHttpClientClass = cp.get(OK_HTTP_CLIENT_CLASS_NAME);
            if (okHttpClientClass.isFrozen()) {
                okHttpClientClass.defrost();
            }

            String methodName = "newCall";
            CtMethod m = okHttpClientClass.getDeclaredMethod(methodName);
            m.insertBefore(
                "request = com.finhub.framework.core.enhance.HttpClientHeaderEnhancer.addFromHeadersToOkHttpRequest(request);");
            log.info("TransformClass For OkHttpClient success. className={}", className);
            return okHttpClientClass.toBytecode();
        } catch (Exception e) {
            log.warn("TransformClass For OkHttpClient fail. className={}", className, e);
        }
        return null;
    }

    private byte[] transformClassForHutoolHttpRequest(String className) {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass hutoolHttpRequestClass = cp.get(HUTOOL_HTTP_REQUEST_CLASS_NAME);
            if (hutoolHttpRequestClass.isFrozen()) {
                hutoolHttpRequestClass.defrost();
            }

            String methodName = "send";
            CtMethod m = hutoolHttpRequestClass.getDeclaredMethod(methodName);
            m.insertBefore(
                "com.finhub.framework.core.enhance.HttpClientHeaderEnhancer.addFromHeadersToHutoolHttpRequest(this);");
            log.info("TransformClass For Hutool HttpRequest success. className={}", className);
            return hutoolHttpRequestClass.toBytecode();
        } catch (Exception e) {
            log.warn("TransformClass For Hutool HttpRequest fail. className={}", className, e);
        }
        return null;
    }

    private byte[] transformClassForCloseableHttpClient(String className) {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass closeableHttpClientClass = cp.get(CLOSEABLE_HTTP_CLIENT_CLASS_NAME);
            if (closeableHttpClientClass.isFrozen()) {
                closeableHttpClientClass.defrost();
            }

            CtClass httpHostClass = cp.get(HTTP_HOST_CLASS_NAME);
            CtClass httpRequestClass = cp.get(HTTP_REQUEST_CLASS_NAME);
            CtClass httpContextClass = cp.get(HTTP_CONTEXT_CLASS_NAME);
            CtClass httpUriRequestClass = cp.get(HTTP_URI_REQUEST_CLASS_NAME);
            CtClass responseHandlerClass = cp.get(RESPONSE_HANDLER_CLASS_NAME);

            CtClass[] methodParams0 = {httpHostClass, httpRequestClass, httpContextClass};
            CtClass[] methodParams1 = {httpUriRequestClass, httpContextClass};
            CtClass[] methodParams2 = {httpHostClass, httpRequestClass};
            CtClass[] methodParams3 = {httpHostClass, httpRequestClass, responseHandlerClass, httpContextClass};

            String methodName = "execute";
            List<CtClass[]> methodParamsList =
                Lists.newArrayList(methodParams0, methodParams1, methodParams2, methodParams3);

            for (CtClass[] methodParams : methodParamsList) {
                CtMethod m = closeableHttpClientClass.getDeclaredMethod(methodName, methodParams);
                m.insertBefore(
                    "com.finhub.framework.core.enhance.HttpClientHeaderEnhancer.addFromHeadersToHttpClient(request);");
            }

            log.info("TransformClass For CloseableHttpClient success. className={}", className);
            return closeableHttpClientClass.toBytecode();
        } catch (Exception e) {
            log.warn("TransformClass For CloseableHttpClient fail. className={}", className, e);
        }
        return null;
    }

    private byte[] transformClassForCloseableHttpAsyncClient(String className) {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass closeableHttpAsyncClientClass = cp.get(CLOSEABLE_HTTP_ASYNC_CLIENT_CLASS_NAME);
            if (closeableHttpAsyncClientClass.isFrozen()) {
                closeableHttpAsyncClientClass.defrost();
            }

            CtClass httHostClass = cp.get(HTTP_HOST_CLASS_NAME);
            CtClass httpRequestClass = cp.get(HTTP_REQUEST_CLASS_NAME);
            CtClass httpContextClass = cp.get(HTTP_CONTEXT_CLASS_NAME);
            CtClass httpAsyncRequestProducerClass = cp.get(HTTP_ASYNC_REQUEST_PRODUCER_CLASS_NAME);
            CtClass httpAsyncRequestConsumerClass = cp.get(HTTP_ASYNC_REQUEST_CONSUMER_CLASS_NAME);
            CtClass futureCallBackClass = cp.get(FUTURE_CALL_BACK_CLASS_NAME);

            String methodName = "execute";
            CtClass[] methodParams0 =
                {httpAsyncRequestProducerClass, httpAsyncRequestConsumerClass, futureCallBackClass};
            CtMethod m0 = closeableHttpAsyncClientClass.getDeclaredMethod(methodName, methodParams0);
            m0.insertBefore(
                "com.finhub.framework.core.enhance.HttpClientHeaderEnhancer.addFromHeadersToHttpClient(requestProducer.generateRequest());");

            CtClass[] methodParams1 = {httHostClass, httpRequestClass, httpContextClass, futureCallBackClass};
            CtMethod m1 = closeableHttpAsyncClientClass.getDeclaredMethod(methodName, methodParams1);
            m1.insertBefore(
                "com.finhub.framework.core.enhance.HttpClientHeaderEnhancer.addFromHeadersToHttpClient(request);");

            log.info("TransformClass For CloseableHttpAsyncClient success. className={}", className);
            return closeableHttpAsyncClientClass.toBytecode();
        } catch (Exception e) {
            log.warn("TransformClass For CloseableHttpAsyncClient fail. className={}", className, e);
        }
        return null;
    }
}
