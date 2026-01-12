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

    <artifactId>${conf.productName}-server</artifactId>
    <version>${r'${'}${conf.productName}.version}</version>
    <packaging>jar</packaging>

    <properties>
        <skip-maven-deploy>true</skip-maven-deploy>
        <start-class>${conf.serverPackageName}.BootStrap</start-class>
    </properties>

    <dependencies>
        <!-- Module -->
        <dependency>
            <groupId>${conf.basePackageName}</groupId>
            <artifactId>${conf.webModuleName}</artifactId>
        </dependency>
        <!-- END -->
    </dependencies>

    <build>
        <finalName>${r'${'}project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
