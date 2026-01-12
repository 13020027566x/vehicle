package com.finhub.framework.web.async;

import com.finhub.framework.core.Func;
import com.finhub.framework.web.async.annotation.EnableWebAsyncConfiguration;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/09/07 15:28
 */
public class WebAsyncEnvironmentProcessor implements EnvironmentPostProcessor, Ordered {

    /**
     * 准备环境中，重写&加载需要异步的路由class文件
     *
     * @param environment   启动环境
     * @param application   spring应用容器
     */
    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        List<String> routeNames = SpringFactoriesLoader.loadFactoryNames(EnableWebAsyncConfiguration.class,
            Thread.currentThread().getContextClassLoader());

        if (Func.isEmpty(routeNames)) {
            return;
        }

        for(String className : routeNames) {
            try {
                WebAsyncAssistSupport.assistClass(className);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 4;
    }

}
