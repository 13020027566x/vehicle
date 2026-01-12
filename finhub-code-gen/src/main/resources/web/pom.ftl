<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>${conf.basePackageName}</groupId>
        <artifactId>${conf.productName}</artifactId>
        <version>${conf.version}-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>${conf.webModuleName}</artifactId>
    <version>${r'${'}${conf.productName}.version}</version>
    <packaging>jar</packaging>

    <properties>
        <skip-maven-deploy>true</skip-maven-deploy>
    </properties>

    <dependencies>
        <!-- Module -->
        <dependency>
            <groupId>${conf.basePackageName}</groupId>
            <artifactId>${conf.serviceModuleName}</artifactId>
        </dependency>
        <!-- END -->

        <dependency>
            <groupId>com.finhub.framework</groupId>
            <artifactId>finhub-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.finhub.framework</groupId>
            <artifactId>finhub-swagger</artifactId>
        </dependency>

        <!-- spring-boot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- END -->

        <!-- web 依赖包 -->
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.1.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- END -->

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
    </dependencies>
</project>
