package com.finhub.framework.web.configuration;

import com.finhub.framework.web.advice.HttpRequestBodyAdvice;
import com.finhub.framework.web.advice.HttpResponseBodyAdvice;
import com.finhub.framework.web.async.WebAsyncTaskThreadPool;
import com.finhub.framework.web.async.interceptor.CustomerCallableProcessingInterceptor;
import com.finhub.framework.web.boot.WebBootProperties;
import com.finhub.framework.web.captcha.CaptchaCacheManager;
import com.finhub.framework.web.csrf.CookieCsrfTokenRepository;
import com.finhub.framework.web.csrf.CsrfInterceptor;
import com.finhub.framework.web.handler.GlobalExceptionHandler;
import com.finhub.framework.web.interceptor.ControllerCostLogInterceptor;
import com.finhub.framework.web.interceptor.TransferSecurityInterceptor;
import com.finhub.framework.web.property.AsyncProperties;
import com.finhub.framework.web.property.FilterProperties;
import com.finhub.framework.web.property.ResponseProperties;

import com.alibaba.druid.support.http.StatViewServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.servlet.Servlet; // 添加这个导入
import static com.finhub.framework.web.constant.WebConstants.EXCLUDE_PATH_LIST;

@Slf4j
@Configuration
@ConditionalOnWebApplication
@Import({FilterConfiguration.class})
@ConditionalOnProperty(name = "vehicle.web.enabled", matchIfMissing = true)
@EnableConfigurationProperties({AsyncProperties.class, FilterProperties.class, ResponseProperties.class, WebBootProperties.class})
public class WebAutoloadConfiguration implements WebMvcConfigurer {

    @Bean
    HttpRequestBodyAdvice httpRequestBodyAdvice() {
        return new HttpRequestBodyAdvice();
    }

    @Bean
    HttpResponseBodyAdvice httpResponseBodyAdvice() {
        return new HttpResponseBodyAdvice();
    }

    @Bean
    GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 验证码 Service
     *
     * @param cacheManager
     * @return
     */
    @Bean
    public CaptchaCacheManager captchaCacheManager(CacheManager cacheManager) {
        CaptchaCacheManager captchaCacheManager = new CaptchaCacheManager();
        captchaCacheManager.setCacheManager(cacheManager);
        captchaCacheManager.setCacheName("halfHour");
        return captchaCacheManager;
    }

    /**
     * DruidStatView Servlet - 修复泛型类型
     */
//    @Bean
//    public ServletRegistrationBean<Servlet> statViewServlet() {
//        // 创建适配器，将 javax.servlet 的 StatViewServlet 包装为 jakarta.servlet.Servlet
//
//        ServletRegistrationBean<Servlet> servletRegistrationBean =
//            new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "123456");
//        servletRegistrationBean.addInitParameter("resetEnable", "true");
//        return servletRegistrationBean;
//    }


    /**
     * 文件上传
     *
     * @return
     */
    @Bean(name = "multipartResolver")
    @ConditionalOnMissingBean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        // Spring Boot 3.x 使用 StandardServletMultipartResolver
        return new StandardServletMultipartResolver();
    }

    /**
     * CSRF Repository
     *
     * @param cacheManager
     * @return
     */
    @Bean
    public CookieCsrfTokenRepository cookieCsrfTokenRepository(CacheManager cacheManager) {
        CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();
        cookieCsrfTokenRepository.setCacheManager(cacheManager);
        cookieCsrfTokenRepository.setCsrfTokenCacheName("oneMinute");
        return cookieCsrfTokenRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerCostLogInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns(EXCLUDE_PATH_LIST)
            .order(Integer.MIN_VALUE);

        TransferSecurityInterceptor transferSecurityInterceptor = new TransferSecurityInterceptor();
        registry.addInterceptor(transferSecurityInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(EXCLUDE_PATH_LIST)
            .order(Integer.MIN_VALUE + 20);

        registry.addInterceptor(new CsrfInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns(EXCLUDE_PATH_LIST)
            .order(Integer.MIN_VALUE + 30);
    }

    /**
     * 发现如果继承了 WebMvcConfigurationSupport，则在 yml 中配置的相关内容会失效。
     * 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/dist/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/dist/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    CustomerCallableProcessingInterceptor customerCallableProcessingInterceptor() {
        return new CustomerCallableProcessingInterceptor();
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(WebAsyncTaskThreadPool.getSingleton());
        configurer.registerCallableInterceptors(customerCallableProcessingInterceptor());
    }
}
