package com.finhub.codegen.conf;

import com.finhub.framework.core.str.StrConstants;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.util.Set;

/**
 * @author Mickey
 * @version 1.0
 * @since 2014/10/18 13:52
 */
@Data
@Slf4j
public class Config {

    private static final String CONF_PATH = "code-gen.properties";

    private String driverClass;

    private String jdbcUrl;

    private String userName;

    private String userPwd;

    private String targetDir;

    private String parentModule;

    private String productName;

    private String dbName;

    private String tables;

    private String author;

    private String version;

    private String finhubVersion;

    private String basePackage;

    private Set<String> tableSet = Sets.newHashSet();

    public Config() {
        try {
            Configuration conf = new PropertiesConfiguration(CONF_PATH);
            initConf(conf);
        } catch (Exception e) {
            log.error("init conf occr exception", e);
        }
    }

    public Config(String path) {
        try {
            File file = new File(path);
            Configuration conf = new PropertiesConfiguration(file);
            initConf(conf);
        } catch (Exception e) {
            log.error("init conf occr exception", e);
        }
    }

    private void initConf(Configuration conf) {
        try {
            this.driverClass =
                conf.getString("driver.class") == null ? StrConstants.S_EMPTY : conf.getString("driver.class").trim();
            this.jdbcUrl = conf.getString("jdbc.url") == null ?
                StrConstants.S_EMPTY :
                conf.getString("jdbc.url").trim().replaceAll("\\{dbName}", this.dbName);
            this.userName =
                conf.getString("userName") == null ? StrConstants.S_EMPTY : conf.getString("userName").trim();
            this.userPwd = conf.getString("userPwd") == null ? StrConstants.S_EMPTY : conf.getString("userPwd").trim();

            this.targetDir =
                conf.getString("target.dir") == null ? StrConstants.S_EMPTY : conf.getString("target.dir").trim();
            this.parentModule =
                conf.getString("parent.module") == null ? StrConstants.S_EMPTY : conf.getString("parent.module").trim();
            this.productName =
                conf.getString("product.name") == null ? StrConstants.S_EMPTY : conf.getString("product.name").trim();
            this.dbName = conf.getString("dbName") == null ? StrConstants.S_EMPTY : conf.getString("dbName").trim();
            this.tables = conf.getString("tables") == null ? StrConstants.S_EMPTY : conf.getString("tables").trim();

            if (!tables.contains(StrConstants.S_ASTERISK)) {
                for (String table : tables.split(StrConstants.S_SEMICOLON)) {
                    tableSet.add(table.trim());
                }
            }

            this.author = conf.getString("author") == null ? StrConstants.S_EMPTY : conf.getString("author").trim();
            this.version = conf.getString("version") == null ? StrConstants.S_EMPTY : conf.getString("version").trim();
            this.finhubVersion = conf.getString("finhub.version") == null ?
                StrConstants.S_EMPTY :
                conf.getString("finhub.version").trim();
            this.basePackage =
                conf.getString("base.package") == null ? StrConstants.S_EMPTY : conf.getString("base.package").trim();

//            this.webModuleName = conf.getString("webModuleName") == null ? StrConstants.S_EMPTY : conf.getString("webModuleName").trim();
//            this.webAppName = conf.getString("webAppName") == null ? StrConstants.S_EMPTY : conf.getString("webAppName").trim();
//            this.apiModuleName = conf.getString("apiModuleName") == null ? StrConstants.S_EMPTY : conf.getString("apiModuleName").trim();
//            this.apiAppName = conf.getString("apiAppName") == null ? StrConstants.S_EMPTY : conf.getString("apiAppName").trim();
//            this.serverModuleName = conf.getString("serverModuleName") == null ? StrConstants.S_EMPTY : conf.getString("serverModuleName").trim();
//            this.clientModuleName = conf.getString("clientModuleName") == null ? StrConstants.S_EMPTY : conf.getString("clientModuleName").trim();
//            this.bizModuleName = conf.getString("bizModuleName") == null ? StrConstants.S_EMPTY : conf.getString("bizModuleName").trim();
//            this.serviceModuleName = conf.getString("serviceModuleName") == null ? StrConstants.S_EMPTY : conf.getString("serviceModuleName").trim();
//            this.daoModuleName = conf.getString("daoModuleName") == null ? StrConstants.S_EMPTY : conf.getString("daoModuleName").trim();
//            this.shiroModuleName = conf.getString("shiroModuleName") == null ? StrConstants.S_EMPTY : conf.getString("shiroModuleName").trim();
//            this.adminFrontName = conf.getString("adminFrontName") == null ? StrConstants.S_EMPTY : conf.getString("adminFrontName").trim();
        } catch (Exception e) {
            log.error("init conf occur exception.", e);
        }
    }

    public String getCreateDate() {
        return DateUtil.date().toDateStr();
    }

    public String getDaoModuleName() {
        return productName + "-dao";
    }

    public String getServiceModuleName() {
        return productName + "-service";
    }

    public String getWebModuleName() {
        return productName + "-web";
    }

    public String getServerModuleName() {
        return productName + "-server";
    }

    public String getRpcApiModuleName() {
        return productName + "-api";
    }

    public String getRpcClientModuleName() {
        return productName + "-client";
    }

    public String getProviderModuleName() {
        return productName + "-provider";
    }

    public String getBasePackageName() {
        if (StrUtil.isBlank(this.basePackage)) {
            return "com." + productName;
        }
        return "com." + this.basePackage;
    }

    public String getDaoPackageName() {
        return this.getBasePackageName() + ".dao";
    }

    public String getServicePackageName() {
        return this.getBasePackageName() + ".service";
    }

    public String getWebPackageName() {
        return this.getBasePackageName() + ".web";
    }

    public String getServerPackageName() {
        return this.getBasePackageName() + ".server";
    }

    public String getRpcApiPackageName() {
        return this.getBasePackageName() + ".api";
    }

    public String getRpcClientPackageName() {
        return this.getBasePackageName() + ".client";
    }

    public String getProviderPackageName() {
        return this.getBasePackageName() + ".provider";
    }

    public String getParentModuleName() {
        return StrUtil.isNotBlank(parentModule) ? parentModule : productName;
    }

}
