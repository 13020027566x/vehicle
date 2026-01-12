package com.finhub.framework.swagger.configuration;

import com.finhub.framework.swagger.property.SwaggerProperties;

import com.google.common.collect.Sets;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
@EnableConfigurationProperties({SwaggerProperties.class})
@ConditionalOnProperty(value = {"spring.profiles.active"}, havingValue = "tx-dev")
public class SwaggerAutoloadConfiguration {

    private static final String APPLICATION_FORM_URLENCODED_UTF8_VALUE =
        "application/x-www-form-urlencoded;charset=UTF-8";

    private static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    @Bean
    public Docket createRestApi(SwaggerProperties swaggerProperties) {
        // 创建 Docket 对象，文档类型，使用 Swagger2
        return new Docket(DocumentationType.SWAGGER_2)
            .protocols(globalProtocols())
            .groupName(swaggerProperties.getGroupName())
            .apiInfo(this.apiInfo()) // 设置 API 信息
            .consumes(globalConsumes())
            .produces(globalProduces())
            .globalRequestParameters(globalRequestParameters())
            .globalResponses(HttpMethod.GET, globalResponses())
            .globalResponses(HttpMethod.HEAD, globalResponses())
            .globalResponses(HttpMethod.POST, globalResponses())
            .globalResponses(HttpMethod.PUT, globalResponses())
            .globalResponses(HttpMethod.PATCH, globalResponses())
            .globalResponses(HttpMethod.DELETE, globalResponses())
            .globalResponses(HttpMethod.OPTIONS, globalResponses())
            .globalResponses(HttpMethod.TRACE, globalResponses())
            // 扫描 Controller 包路径，获得 API 接口
            .select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
            .paths(PathSelectors.any())
            // 构建出 Docket 对象
            .build().securitySchemes(securitySchemes());
    }

    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("finhub 接口文档")
            .description("我是一段描述")
            .version("1.0.0") // 版本号
            .contact(new Contact("finhub", "http://www.finhub.com", "mingkai.tao@finhub.com")) // 联系人
            .build();
    }

    private Set<String> globalProtocols() {
        return Sets.newHashSet();
    }

    private Set<String> globalConsumes() {
        return Sets.newHashSet(APPLICATION_FORM_URLENCODED_UTF8_VALUE);
    }

    private Set<String> globalProduces() {
        return Sets.newHashSet(APPLICATION_JSON_UTF8_VALUE);
    }

    /**
     * 所有请求都添加的统一参数(或者头)信息
     *
     * @return
     */
    private List<RequestParameter> globalRequestParameters() {
        return new ArrayList<>();
    }

    /**
     * 所有请求响应都添加统一响应信息
     *
     * @return
     */
    private List<Response> globalResponses() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("400").description("请求参数错误").build());
        responseList.add(new ResponseBuilder().code("401").description("未授权/授权失败").build());
        responseList.add(new ResponseBuilder().code("404").description("路由不存在").build());
        responseList.add(new ResponseBuilder().code("405").description("不支持的请求方法").build());
        responseList.add(new ResponseBuilder().code("500").description("fail").build());
        return responseList;
    }

    /**
     * 安全访问 API
     *
     * @return
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> list = new ArrayList<>();
        /*
         * name：页面显示名称
         * keyname：自动携带的参数名称
         * passAs：自动携带的参数类型
         */
        list.add(new ApiKey("Authorization", "sid", "header"));
        return list;
    }

}
