# Spring 配置项 运行项目需要在vm命令行中增加spring.profiles.active=环境变量 例如tx-dev
spring:
    application:
        name: ${r'@'}project.build.finalName@

# 新工程默认开启的配置
vehicle:
    json:
        serializationInclusion: ALWAYS
    response:
        enable: true
        successCode: 0
        fillDataObjectEnable: true
