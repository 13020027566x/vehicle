package com.finhub.framework.agent;

import com.finhub.framework.agent.transformer.CustomClassFileTransformer;

import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.lang.instrument.Instrumentation;

/**
 * BootAgent 启动入口
 *
 * @author taomingkai
 * @version 1.0.0
 * @since 2022/09/16 11:29 上午
 */
@Slf4j
public class BootAgent {

    /**
     * 以vm参数的形式载入，在程序main方法执行之前执行
     * 其jar包的manifest需要配置属性Premain-Class
     */
    public static void premain(String args, Instrumentation inst) {
        customTransformer(args, inst);
    }

    /**
     * 以Attach的方式载入，在Java程序启动后执行
     * 其jar包的manifest需要配置属性Agent-Class
     */
    public static void agentmain(String args, Instrumentation inst) {
        customTransformer(args, inst);
    }

    private static void customTransformer(String args, Instrumentation inst) {
        // 指定我们自己定义的 Transformer，在其中利用 Javassist 做字节码替换
        inst.addTransformer(new CustomClassFileTransformer(), true);
        try {
            // 重定义类并载入新的字节码
            inst.retransformClasses(CloseableHttpClient.class, CloseableHttpAsyncClient.class, HttpRequest.class);
            log.info("BootAgent Load Success.");
        } catch (Exception e) {
            log.warn("BootAgent Load Failed.", e);
        }
    }
}
