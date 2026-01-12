<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>com.finhub</groupId>
        <artifactId>finhub-root</artifactId>
        <version>${conf.finhubVersion}</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <groupId>${conf.basePackageName}</groupId>
    <artifactId>${conf.productName}</artifactId>
    <version>${conf.version}-SNAPSHOT</version>

    <packaging>pom</packaging>

    <properties>
        <${conf.productName}.version>${conf.version}${r'${'}current.version}</${conf.productName}.version>
    </properties>

    <modules>
        <module>${conf.rpcApiModuleName}</module>
        <module>${conf.rpcClientModuleName}</module>
        <module>${conf.daoModuleName}</module>
        <module>${conf.providerModuleName}</module>
        <module>${conf.serverModuleName}</module>
        <module>${conf.serviceModuleName}</module>
        <module>${conf.webModuleName}</module>
    </modules>

    <dependencies>
        <!-- Module -->
        <!-- END -->
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <!-- Module -->
            <dependency>
                <groupId>${conf.basePackageName}</groupId>
                <artifactId>${conf.rpcApiModuleName}</artifactId>
                <version>${r'${'}${conf.productName}.version}</version>
            </dependency>
            <dependency>
                <groupId>${conf.basePackageName}</groupId>
                <artifactId>${conf.rpcClientModuleName}</artifactId>
                <version>${r'${'}${conf.productName}.version}</version>
            </dependency>
            <dependency>
                <groupId>${conf.basePackageName}</groupId>
                <artifactId>${conf.daoModuleName}</artifactId>
                <version>${r'${'}${conf.productName}.version}</version>
            </dependency>
            <dependency>
                <groupId>${conf.basePackageName}</groupId>
                <artifactId>${conf.providerModuleName}</artifactId>
                <version>${r'${'}${conf.productName}.version}</version>
            </dependency>
            <dependency>
                <groupId>${conf.basePackageName}</groupId>
                <artifactId>${conf.serverModuleName}</artifactId>
                <version>${r'${'}${conf.productName}.version}</version>
            </dependency>
            <dependency>
                <groupId>${conf.basePackageName}</groupId>
                <artifactId>${conf.serviceModuleName}</artifactId>
                <version>${r'${'}${conf.productName}.version}</version>
            </dependency>
            <dependency>
                <groupId>${conf.basePackageName}</groupId>
                <artifactId>${conf.webModuleName}</artifactId>
                <version>${r'${'}${conf.productName}.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
