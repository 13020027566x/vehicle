package com.finhub.framework.core.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * coreBeanPostProcessor 统一处理一些特殊bean逻辑
 *
 * @author : liuwei
 * @date : 2021/12/2
 * @desc :
 */
@Slf4j
public class CoreBeanPostProcessor implements BeanPostProcessor {

    private static final String ACTUATOR_HEALTH_PATH_ROUTE = "/actuator/**";


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        this.ignoreInterceptorInActuatorRoute(bean);
        this.advanceLoadingDataSourceConnection(bean);
        this.filterSupportWebAsync(bean);
        return bean;
    }

    /**
     * 拦截器忽略对actuator健康检测的路由
     *
     * @param bean
     */
    public void ignoreInterceptorInActuatorRoute(Object bean) {
        if (!(bean instanceof InterceptorRegistration)) {
            return;
        }
        InterceptorRegistration interceptorRegistration = (InterceptorRegistration) bean;
        interceptorRegistration.excludePathPatterns(ACTUATOR_HEALTH_PATH_ROUTE);
    }

    /**
     * 创建dataSource时 提前加载数据库连接
     * 问题： 1.由于sky-walking对数据库增强 在数据库初始化连接时 数据库需要设置编码java.nio.charset.CharSets,
     *          底层FastCharsetProvider.charsetForName()采用加锁同步安全方式，会将StandardCharsets对象进行锁住，
     *          而后sky-walking进行字节码加强时需要通过加载jar方式，会从JarFileFactory进行取锁。
     *       2.与此同时sentinel每三秒会进行一次心跳检测，当sentinel触发心跳检测时，sky-walking进行字节码加强时，会先尝试获取JarFileFactory锁，
     *          获取成功后。会设置字符编码，则尝试获取StandardCharsets对象锁。
     *       1：获取锁的顺序 StandardCharsets对象锁 -> JarFileFactory对象锁
     *       2：获取锁的顺序 JarFileFactory对象锁 -> StandardCharsets对象锁
     *      上述同一时间触发可能会导致死锁条件
     * 目的：上述死锁原因确定后，需要打破死锁条件
     * 前期方案：提前加载数据库连接池，数据库连接池是在第一次使用数据库时创建，后面查询修改连接都是从已经初始化好的池中获取。
     *         提前加载数据库连接池，会优先于项目启动，可以避免在sentinel心跳&初始化连接池产生的加载锁顺序问题
     *
     * 最终方案：从sky-walking角度来解决问题。sky-walking设置字符编码的方式重写为非同步。
     *
     * @param bean
     */
    private void advanceLoadingDataSourceConnection(Object bean) {
        if (!(bean instanceof DataSource)) {
            return;
        }
        Connection connection = null;
        try {
            connection = ((DataSource) bean).getConnection();
        } catch (SQLException e) {
            log.warn("advance loading DB connection fail : ", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * webFilter过滤器开启异步支持，@WebFilter 默认不支持
     * @param bean
     */
    private void filterSupportWebAsync(Object bean) {
        if (!(bean instanceof FilterRegistrationBean)) {
            return;
        }
        ((FilterRegistrationBean) bean).setAsyncSupported(true);
    }

}
