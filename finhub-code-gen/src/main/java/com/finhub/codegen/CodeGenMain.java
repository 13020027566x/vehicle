package com.finhub.codegen;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.framework.core.file.FileUtils;
import com.finhub.framework.core.str.StrConstants;

import cn.hutool.core.io.resource.ResourceUtil;
import com.finhub.codegen.conf.Config;
import com.finhub.codegen.db.DbInfo;
import com.finhub.codegen.db.TableInfo;
import com.finhub.codegen.model.PathInfo;
import com.finhub.codegen.util.TemplateUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @author Mickey
 * @version 1.0
 * @since 2014/10/20 15:44
 */
@Slf4j
public class CodeGenMain {
    private static final String PARENT_FTL_FILE_NAME = "parent.ftl";

    private static final String RELATIVE_PARENT_TEMPLATE_PATH = "/";
    private static final String RELATIVE_DAO_TEMPLATE_PATH = "/dao";
    private static final String RELATIVE_SERVICE_TEMPLATE_PATH = "/service";
    private static final String RELATIVE_WEB_TEMPLATE_PATH = "/web";
    private static final String RELATIVE_SERVER_TEMPLATE_PATH = "/server";
    private static final String RELATIVE_RPC_API_TEMPLATE_PATH = "/api";
    private static final String RELATIVE_RPC_CLIENT_TEMPLATE_PATH = "/client";
    private static final String RELATIVE_RPC_TEMPLATE_PATH = "/provider";


    public static void main(final String[] args) throws Exception {
        URL url = ResourceUtil.getResource(PARENT_FTL_FILE_NAME);
        String classPath = url.getPath().replace(File.separator + PARENT_FTL_FILE_NAME, StrConstants.S_EMPTY);
        String type = url.getProtocol();
        log.info("classPath:{},type:{}", classPath, type);

        Config conf;
        if (Func.isEmpty(args)) {
            if (StrConstants.S_JAR.equals(type)) {
                log.info("参数异常，缺少配置文件路径参数！");
                return;
            }
            //使用默认路径
            conf = new Config();
        } else {
            String confPath = args[0];
            log.info("conf path:{}", confPath);
            conf = new Config(confPath);
        }

        DbInfo dbInfo = DbInfo.getInstance(conf);
        genCodeForBoot(conf, dbInfo, classPath, type);
        if (!StrConstants.S_JAR.equals(type)) {
            genCodeForRpc(conf, dbInfo, classPath, type);
        }
        // genCodeForFront(conf, dbInfo);
    }

    private static void genCodeForRpc(final Config conf, final DbInfo dbInfo, final String classpath, final String type)
        throws Exception {
        Map<String, Object> model = Maps.newHashMap();
        model.put("conf", conf);

        log.info(
            "\n--------------------------------------------------------------Generate RpcApi Module Start--------------------------------------------------------------");
        genRpcApiModule(conf.getRpcApiModuleName(), model,
            classpath + RELATIVE_RPC_API_TEMPLATE_PATH,
            RELATIVE_RPC_API_TEMPLATE_PATH,
            conf.getTargetDir() + "/" + conf.getParentModuleName() + "/" + conf.getRpcApiModuleName(), dbInfo,
            conf.getRpcApiPackageName().replace(".", "/"), type);
        log.info(
            "\n--------------------------------------------------------------Generate RpcApi Module End  --------------------------------------------------------------");

        log.info(
            "\n--------------------------------------------------------------Generate RpcClient Module Start--------------------------------------------------------------");
        genRpcClientModule(conf.getRpcClientModuleName(), model,
            classpath + RELATIVE_RPC_CLIENT_TEMPLATE_PATH,
            RELATIVE_RPC_CLIENT_TEMPLATE_PATH,
            conf.getTargetDir() + "/" + conf.getParentModuleName() + "/" + conf.getRpcClientModuleName(), dbInfo,
            conf.getRpcClientPackageName().replace(".", "/"), type);
        log.info(
            "\n--------------------------------------------------------------Generate RpcClient Module End  --------------------------------------------------------------");

        log.info(
            "\n--------------------------------------------------------------Generate RpcImpl Module Start--------------------------------------------------------------");
        genProviderModule(conf.getProviderModuleName(), model,
            classpath + RELATIVE_RPC_TEMPLATE_PATH,
            RELATIVE_RPC_TEMPLATE_PATH,
            conf.getTargetDir() + "/" + conf.getParentModuleName() + "/" + conf.getProviderModuleName(), dbInfo,
            conf.getProviderPackageName().replace(".", "/"), type);
        log.info(
            "\n--------------------------------------------------------------Generate RpcImpl Module End  --------------------------------------------------------------");
    }

    private static void genRpcApiModule(final String rpcApiModuleName, final Map<String, Object> model,
        final String rpcApiTemplatePath, final String relativeRpcApiTemplatePath, final String rpcApiTargetPath,
        final DbInfo dbInfo,
        final String rpcApiPackagePath, final String type) throws IOException {
        log.info("Generate " + rpcApiModuleName + " Module : " + rpcApiTargetPath + "/src/test/java");
        FileUtils.createDir(rpcApiTargetPath + "/src/test/java", false);
        log.info("==> success");

        log.info("Generate " + rpcApiModuleName + " Module : " + rpcApiTargetPath + "/src/main/java");
        FileUtils.createDir(rpcApiTargetPath + "/src/main/java", false);
        log.info("==> success");

        log.info("Generate " + rpcApiModuleName + " Module : " + rpcApiTargetPath + "/src/main/resources");
        FileUtils.createDir(rpcApiTargetPath + "/src/main/resources", false);
        log.info("==> success");

        executeFreemarker(rpcApiModuleName, model,
            new PathInfo(rpcApiTemplatePath, relativeRpcApiTemplatePath, "pom.ftl", rpcApiTargetPath, "pom.xml"), type);
        executeFreemarker(rpcApiModuleName, model,
            new PathInfo(rpcApiTemplatePath, relativeRpcApiTemplatePath, "DemoReqDTO.ftl",
                rpcApiTargetPath + "/src/main/java" + File.separator + rpcApiPackagePath + "/demo/dto",
                "DemoReqDTO.java"), type);
        executeFreemarker(rpcApiModuleName, model,
            new PathInfo(rpcApiTemplatePath, relativeRpcApiTemplatePath, "DemoResDTO.ftl",
                rpcApiTargetPath + "/src/main/java" + File.separator + rpcApiPackagePath + "/demo/dto",
                "DemoResDTO.java"), type);
        executeFreemarker(rpcApiModuleName, model,
            new PathInfo(rpcApiTemplatePath, relativeRpcApiTemplatePath, "DemoService.ftl",
                rpcApiTargetPath + "/src/main/java" + File.separator + rpcApiPackagePath + "/demo", "DemoService.java"),
            type);
    }

    private static void genRpcClientModule(final String rpcClientModuleName, final Map<String, Object> model,
        final String rpcClientTemplatePath, final String relativeRpcClientTemplatePath,
        final String rpcClientTargetPath, final DbInfo dbInfo,
        final String rpcClientPackagePath, final String type) throws IOException {
        log.info("Generate " + rpcClientModuleName + " Module : " + rpcClientTargetPath + "/src/test/java");
        FileUtils.createDir(rpcClientTargetPath + "/src/test/java", false);
        log.info("==> success");

        log.info("Generate " + rpcClientModuleName + " Module : " + rpcClientTargetPath + "/src/test/resources");
        FileUtils.createDir(rpcClientTargetPath + "/src/test/resources/META-INF", false);
        log.info("==> success");

        log.info("Generate " + rpcClientModuleName + " Module : " + rpcClientTargetPath + "/src/main/java");
        FileUtils.createDir(rpcClientTargetPath + "/src/main/java", false);
        log.info("==> success");

        log.info(
            "Generate " + rpcClientModuleName + " Module : " + rpcClientTargetPath + "/src/main/resources/META-INF");
        FileUtils.createDir(rpcClientTargetPath + "/src/main/resources", false);
        log.info("==> success");

        executeFreemarker(rpcClientModuleName, model,
            new PathInfo(rpcClientTemplatePath, relativeRpcClientTemplatePath, "ClientAutoloadConfiguration.ftl",
                rpcClientTargetPath + "/src/main/java" + File.separator + rpcClientPackagePath + "/configuration",
                "ClientAutoloadConfiguration.java"), type);
        executeFreemarker(rpcClientModuleName, model,
            new PathInfo(rpcClientTemplatePath, relativeRpcClientTemplatePath, "DemoManager.ftl",
                rpcClientTargetPath + "/src/main/java" + File.separator + rpcClientPackagePath + "/demo/manager",
                "DemoManager.java"), type);
        executeFreemarker(rpcClientModuleName, model,
            new PathInfo(rpcClientTemplatePath, relativeRpcClientTemplatePath, "DemoManagerTest.ftl",
                rpcClientTargetPath + "/src/test/java" + File.separator + rpcClientPackagePath + "/demo/manager",
                "DemoManagerTest.java"), type);
        executeFreemarker(rpcClientModuleName, model,
            new PathInfo(rpcClientTemplatePath, relativeRpcClientTemplatePath, "pom.ftl", rpcClientTargetPath,
                "pom.xml"), type);
        executeFreemarker(rpcClientModuleName, model,
            new PathInfo(rpcClientTemplatePath, relativeRpcClientTemplatePath, "spring.ftl",
                rpcClientTargetPath + "/src/main/resources/META-INF",
                "spring.factories"), type);
        executeFreemarker(rpcClientModuleName, model,
            new PathInfo(rpcClientTemplatePath, relativeRpcClientTemplatePath, "application.ftl",
                rpcClientTargetPath + "/src/test/resources",
                "application.yaml"), type);

        copyDirectory(rpcClientModuleName, rpcClientTemplatePath + "/i18n",
            rpcClientTargetPath + "/src/test/resources/i18n");
    }

    private static void genProviderModule(final String providerModuleName, final Map<String, Object> model,
        final String rpcTemplatePath, final String relativeRpcTemplatePath, final String rpcTargetPath,
        final DbInfo dbInfo, final String rpcPackagePath, final String type)
        throws IOException {
        log.info("Generate " + providerModuleName + " Module : " + rpcTargetPath + "/src/test/java");
        FileUtils.createDir(rpcTargetPath + "/src/test/java", false);
        log.info("==> success");

        log.info("Generate " + providerModuleName + " Module : " + rpcTargetPath + "/src/main/java");
        FileUtils.createDir(rpcTargetPath + "/src/main/java", false);
        log.info("==> success");

        log.info("Generate " + providerModuleName + " Module : " + rpcTargetPath + "/src/main/resources/META-INF");
        FileUtils.createDir(rpcTargetPath + "/src/main/resources", false);
        log.info("==> success");

        executeFreemarker(providerModuleName, model,
            new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "DemoConverter.ftl",
                rpcTargetPath + "/src/main/java" + File.separator + rpcPackagePath + "/demo/converter",
                "DemoConverter.java"), type);
        executeFreemarker(providerModuleName, model,
            new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "DemoDO.ftl",
                rpcTargetPath + "/src/main/java" + File.separator + rpcPackagePath + "/demo/domain",
                "DemoDO.java"), type);
        executeFreemarker(providerModuleName, model,
            new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "DemoDTO.ftl",
                rpcTargetPath + "/src/main/java" + File.separator + rpcPackagePath + "/demo/dto",
                "DemoDTO.java"), type);
        executeFreemarker(providerModuleName, model,
            new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "DemoManager.ftl",
                rpcTargetPath + "/src/main/java" + File.separator + rpcPackagePath + "/demo/manager",
                "DemoManager.java"), type);
        executeFreemarker(providerModuleName, model,
            new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "DemoServiceImpl.ftl",
                rpcTargetPath + "/src/main/java" + File.separator + rpcPackagePath + "/demo/impl",
                "DemoServiceImpl.java"), type);
        executeFreemarker(providerModuleName, model,
            new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "pom.ftl", rpcTargetPath, "pom.xml"), type);
        executeFreemarker(providerModuleName, model,
            new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "ProviderConfiguration.ftl",
                rpcTargetPath + "/src/main/java" + File.separator + rpcPackagePath + "/configuration",
                "ProviderConfiguration.java"), type);
        executeFreemarker(providerModuleName, model, new PathInfo(rpcTemplatePath, relativeRpcTemplatePath, "ProviderServer.ftl",
            rpcTargetPath + "/src/main/java" + File.separator + rpcPackagePath, "ProviderServer.java"), type);
    }

//    private static void genCodeForFront(Config conf, DbInfo dbInfo) {
//        Map<String, Object> model = Maps.newHashMap();
//        model.put("conf", conf);
//
//        for (TableInfo table : dbInfo.getTables()) {
//            model.put("table", table);
//
//            String templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/app/form";
//            String templateName = "add.ftl";
//            String targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/app" + File.separator + table.getCamelName() + "/form";
//            String targetName = "add.vue";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/app/form";
//            templateName = "modify.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/app" + File.separator + table.getCamelName() + "/form";
//            targetName = "modify.vue";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/app/form";
//            templateName = "remove.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/app" + File.separator + table.getCamelName() + "/form";
//            targetName = "remove.vue";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/app/form";
//            templateName = "show.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/app" + File.separator + table.getCamelName() + "/form";
//            targetName = "show.vue";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/app";
//            templateName = "index.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/app" + File.separator + table.getCamelName();
//            targetName = "index.vue";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/app/i18n";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/app" + File.separator + table.getCamelName() + "/i18n";
//            copyDirectory(conf.getAdminFrontName(), templatePath, targetPath);
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front";
//            templateName = "models.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/models";
//            targetName = table.getCamelName() + ".js";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front";
//            templateName = "routes.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/router/app";
//            targetName = table.getCamelName() + ".js";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/store";
//            templateName = "actions.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/store/modules" + File.separator + table.getCamelName();
//            targetName = "actions.js";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/store";
//            templateName = "getters.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/store/modules" + File.separator + table.getCamelName();
//            targetName = "getters.js";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/store";
//            templateName = "index.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/store/modules" + File.separator + table.getCamelName();
//            targetName = "index.js";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/store";
//            templateName = "mutations.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/store/modules" + File.separator + table.getCamelName();
//            targetName = "mutations.js";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//
//            templatePath = PathUtils.getWebRootPath() + File.separator + "src/main/resources/jeasy-admin-front/store";
//            templateName = "types.ftl";
//            targetPath = StrUtil.subPre(PathUtils.getWebRootPath(), PathUtils.getWebRootPath().lastIndexOf(File.separator)) + File.separator + "jeasy-admin-front/src/store/modules" + File.separator + table.getCamelName();
//            targetName = "types.js";
//            executeFreemarker(conf.getAdminFrontName(), model, new PathInfo(templatePath, templateName, targetPath, targetName));
//        }
//    }

    private static void copyDirectory(String moduleName, String templatePath, String targetPath) {
        log.info("Generate " + moduleName + " Module : " + targetPath);
        FileUtils.createDir(targetPath, false);
        log.info("==> success");
        log.info("Generate " + moduleName + " Module : " + targetPath);
        FileUtils.copyDirectiory(new File(templatePath), targetPath);
        log.info("==> success");
    }

    private static void executeFreemarker(String moduleName, Map<String, Object> model,
        PathInfo pathInfo, String type) {
        log.info("Generate " + moduleName + " Module : " + pathInfo.getTargetPath() + File.separator
            + pathInfo.getTargetName());
        TemplateUtils.executeFreemarker(pathInfo.getTemplatePath(), pathInfo.getRelativeTemplatePath(),
            pathInfo.getTemplateName(),
            CharsetUtils.DEFAULT_ENCODE, model, pathInfo.getTargetPath(), pathInfo.getTargetName(), type);
        log.info("==> success");
    }

    private static void genCodeForBoot(final Config conf, final DbInfo dbInfo, final String classpath,
        final String type)
        throws Exception {
        Map<String, Object> model = Maps.newHashMap();
        model.put("conf", conf);

        if (!StrConstants.S_JAR.equals(type)) {
            log.info(
                "\n--------------------------------------------------------------Generate Parent Module Start--------------------------------------------------------------");
            genParentModule(conf.getParentModuleName(), model,
                classpath + RELATIVE_PARENT_TEMPLATE_PATH,
                RELATIVE_PARENT_TEMPLATE_PATH,
                conf.getTargetDir() + File.separator + conf.getParentModuleName(), type);
            log.info(
                "\n--------------------------------------------------------------Generate Parent Module End  --------------------------------------------------------------");


            log.info(
                "\n--------------------------------------------------------------Generate Server Module Start--------------------------------------------------------------");
            genServerModule(conf.getServerModuleName(), model,
                classpath + RELATIVE_SERVER_TEMPLATE_PATH,
                RELATIVE_SERVER_TEMPLATE_PATH,
                conf.getTargetDir() + "/" + conf.getParentModuleName() + "/" + conf.getServerModuleName(), dbInfo,
                conf.getServerPackageName().replace(".", "/"), type);
            log.info(
                "\n--------------------------------------------------------------Generate Server Module End  --------------------------------------------------------------");
        }

        log.info(
            "\n--------------------------------------------------------------Generate Dao Module Start--------------------------------------------------------------");
        genDaoModule(conf.getDaoModuleName(), model,
            classpath + RELATIVE_DAO_TEMPLATE_PATH,
            RELATIVE_DAO_TEMPLATE_PATH,
            conf.getTargetDir() + "/" + conf.getParentModuleName() + "/" + conf.getDaoModuleName(), dbInfo,
            conf.getDaoPackageName().replace(".", "/"), type);
        log.info(
            "\n--------------------------------------------------------------Generate Dao Module End  --------------------------------------------------------------");

        log.info(
            "\n--------------------------------------------------------------Generate Service Module Start--------------------------------------------------------------");
        genServiceModule(conf.getServiceModuleName(), model,
            classpath + RELATIVE_SERVICE_TEMPLATE_PATH,
            RELATIVE_SERVICE_TEMPLATE_PATH,
            conf.getTargetDir() + "/" + conf.getParentModuleName() + "/" + conf.getServiceModuleName(), dbInfo,
            conf.getServicePackageName().replace(".", "/"), type);
        log.info(
            "\n--------------------------------------------------------------Generate Service Module End  --------------------------------------------------------------");

        log.info(
            "\n--------------------------------------------------------------Generate Web Module Start--------------------------------------------------------------");
        genWebModule(conf.getWebModuleName(), model,
            classpath + RELATIVE_WEB_TEMPLATE_PATH,
            RELATIVE_WEB_TEMPLATE_PATH,
            conf.getTargetDir() + "/" + conf.getParentModuleName() + "/" + conf.getWebModuleName(), dbInfo,
            conf.getWebPackageName().replace(".", "/"), type);
        log.info(
            "\n--------------------------------------------------------------Generate Web Module End  --------------------------------------------------------------");
    }

    private static void genParentModule(String parentModuleName, final Map<String, Object> model,
        final String parentTemplatePath, final String relativeParentTemplatePath,
        final String parentTargetPath, final String type) {
        log.info("Generate " + parentModuleName + " Module");
        FileUtils.createDir(parentTargetPath, false);
        log.info("==> success");

        copyDirectory(parentModuleName, parentTemplatePath + "/parent", parentTargetPath + "/");

        executeFreemarker(parentModuleName, model,
            new PathInfo(parentTemplatePath, relativeParentTemplatePath, "parent.ftl", parentTargetPath, "pom.xml"),
            type);
    }

    private static void genDaoModule(final String daoModuleName, final Map<String, Object> model,
        final String daoTemplatePath, final String relativeDaoTemplatePath, final String daoTargetPath,
        final DbInfo dbInfo, final String daoPackagePath, final String type)
        throws IOException {
        model.put("isMysql", dbInfo.getIsMysql());
        log.info("Generate " + daoModuleName + " Module : " + daoTargetPath + "/src/test/java");
        FileUtils.createDir(daoTargetPath + "/src/test/java", false);
        log.info("==> success");

        log.info("Generate " + daoModuleName + " Module : " + daoTargetPath + "/src/main/java");
        FileUtils.createDir(daoTargetPath + "/src/main/java", false);
        log.info("==> success");

        log.info("Generate " + daoModuleName + " Module : " + daoTargetPath + "/src/main/resources");
        FileUtils.createDir(daoTargetPath + "/src/main/resources", false);
        log.info("==> success");

        executeFreemarker(daoModuleName, model,
            new PathInfo(daoTemplatePath, relativeDaoTemplatePath, "pom.ftl", daoTargetPath, "pom.xml"), type);
        executeFreemarker(daoModuleName, model,
            new PathInfo(daoTemplatePath, relativeDaoTemplatePath, "DaoConfiguration.ftl",
                daoTargetPath + "/src/main/java" + File.separator + daoPackagePath + "/configuration",
                "DaoConfiguration.java"), type);

        for (TableInfo table : dbInfo.getTables()) {
            model.put("table", table);

            // PO
            String poTargetPath = daoTargetPath + "/src/main/java" + File.separator + daoPackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "po";
            String poTargetName = table.getClassName() + "PO.java";
            executeFreemarker(daoModuleName, model,
                new PathInfo(daoTemplatePath, relativeDaoTemplatePath, "PO.ftl", poTargetPath, poTargetName), type);

            // DAO
            String dao2TargetPath = daoTargetPath + "/src/main/java" + File.separator + daoPackagePath + File.separator
                + table.getLowerCamelName();
            String dao2TargetName = table.getClassName() + "DAO.java";
            executeFreemarker(daoModuleName, model,
                new PathInfo(daoTemplatePath, relativeDaoTemplatePath, "DAO.ftl", dao2TargetPath, dao2TargetName),
                type);

            // Mapper
            String mapperTargetPath = daoTargetPath + "/src/main/resources/sqlMapper";
            String mapperTargetName = table.getClassName() + "Mapper.xml";
            executeFreemarker(daoModuleName, model,
                new PathInfo(daoTemplatePath, relativeDaoTemplatePath, "Mapper.ftl", mapperTargetPath,
                    mapperTargetName), type);
        }
    }

    private static void genServiceModule(final String serviceModuleName, final Map<String, Object> model,
        final String serviceTemplatePath, final String relativeServiceTemplatePath, final String serviceTargetPath,
        final DbInfo dbInfo,
        final String servicePackagePath, final String type) throws IOException {
        log.info("Generate " + serviceModuleName + " Module : " + serviceTargetPath + "/src/test/java");
        FileUtils.createDir(serviceTargetPath + "/src/test/java", false);
        log.info("==> success");

        log.info("Generate " + serviceModuleName + " Module : " + serviceTargetPath + "/src/main/java");
        FileUtils.createDir(serviceTargetPath + "/src/main/java", false);
        log.info("==> success");

        log.info("Generate " + serviceModuleName + " Module : " + serviceTargetPath + "/src/main/resources");
        FileUtils.createDir(serviceTargetPath + "/src/main/resources", false);
        log.info("==> success");

        executeFreemarker(serviceModuleName, model,
            new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "pom.ftl", serviceTargetPath, "pom.xml"),
            type);
        executeFreemarker(serviceModuleName, model,
            new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "ServiceConfiguration.ftl",
                serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + "/configuration",
                "ServiceConfiguration.java"), type);
        executeFreemarker(serviceModuleName, model,
            new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "BaseJUnitTester.ftl",
                serviceTargetPath + "/src/test/java" + File.separator + servicePackagePath, "BaseJUnitTester.java"),
            type);
        executeFreemarker(serviceModuleName, model,
            new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "ServiceTestConfiguration.ftl",
                serviceTargetPath + "/src/test/java" + File.separator + servicePackagePath + "/configuration",
                "ServiceTestConfiguration.java"), type);

        for (TableInfo table : dbInfo.getTables()) {
            model.put("table", table);

            // Converter
            String converterTargetPath =
                serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                    + table.getLowerCamelName() + File.separator + "converter";
            String converterTargetName = table.getClassName() + "Converter.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "Converter.ftl", converterTargetPath,
                    converterTargetName), type);

            // DO
            String doTargetPath =
                serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                    + table.getLowerCamelName() + File.separator + "domain";
            String doTargetName = table.getClassName() + "DO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "DO.ftl", doTargetPath, doTargetName),
                type);

            // JUnit
            String junitTargetPath =
                serviceTargetPath + "/src/test/java" + File.separator + servicePackagePath + File.separator
                    + table.getLowerCamelName() + File.separator + "domain";
            String junitTargetName = table.getClassName() + "DOTest.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "DOTest.ftl", junitTargetPath,
                    junitTargetName), type);

            // Service
            String service2TargetPath =
                serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                    + table.getLowerCamelName();
            String service2TargetName = table.getClassName() + "Service.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "Service.ftl", service2TargetPath,
                    service2TargetName), type);

            // Service Impl
            String serviceImplTargetPath =
                serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                    + table.getLowerCamelName() + File.separator + "impl";
            String serviceImplTargetName = table.getClassName() + "ServiceImpl.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "ServiceImpl.ftl", serviceImplTargetPath,
                    serviceImplTargetName), type);

            // Manager
            String managerTargetPath =
                serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                    + table.getLowerCamelName() + File.separator + "manager";
            String managerTargetName = table.getClassName() + "Manager.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "Manager.ftl", managerTargetPath,
                    managerTargetName), type);

            // DTO
            String dtoTargetPath =
                serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                    + table.getLowerCamelName() + File.separator + "dto";
            String dtoTargetName = table.getClassName() + "AddReqDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "AddReqDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "ListReqDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "ListReqDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "ListResDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "ListResDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "ModifyReqDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "ModifyReqDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "PageReqDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "PageReqDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "PageResDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "PageResDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "RemoveReqDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "RemoveReqDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "ShowResDTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "ShowResDTO.ftl", dtoTargetPath,
                    dtoTargetName), type);

            dtoTargetPath = serviceTargetPath + "/src/main/java" + File.separator + servicePackagePath + File.separator
                + table.getLowerCamelName() + File.separator + "dto";
            dtoTargetName = table.getClassName() + "DTO.java";
            executeFreemarker(serviceModuleName, model,
                new PathInfo(serviceTemplatePath, relativeServiceTemplatePath, "DTO.ftl", dtoTargetPath, dtoTargetName),
                type);
        }
    }

    private static void genWebModule(final String webModuleName, final Map<String, Object> model,
        final String webTemplatePath, final String relativeWebTemplatePath, final String webTargetPath,
        final DbInfo dbInfo, final String webPackagePath, final String type)
        throws IOException {
        log.info("Generate " + webModuleName + " Module : " + webTargetPath + "/src/test/java");
        FileUtils.createDir(webTargetPath + "/src/test/java", false);
        log.info("==> success");

        log.info("Generate " + webModuleName + " Module : " + webTargetPath + "/src/main/java");
        FileUtils.createDir(webTargetPath + "/src/main/java", false);
        log.info("==> success");

        log.info("Generate " + webModuleName + " Module : " + webTargetPath + "/src/main/resources");
        FileUtils.createDir(webTargetPath + "/src/main/resources", false);
        log.info("==> success");

        executeFreemarker(webModuleName, model,
            new PathInfo(webTemplatePath, relativeWebTemplatePath, "pom.ftl", webTargetPath, "pom.xml"), type);
        executeFreemarker(webModuleName, model,
            new PathInfo(webTemplatePath, relativeWebTemplatePath, "WebConfiguration.ftl",
                webTargetPath + "/src/main/java" + File.separator + webPackagePath + "/configuration",
                "WebConfiguration.java"), type);
        executeFreemarker(webModuleName, model, new PathInfo(webTemplatePath, relativeWebTemplatePath, "WebServer.ftl",
            webTargetPath + "/src/main/java" + File.separator + webPackagePath, "WebServer.java"), type);

        for (TableInfo table : dbInfo.getTables()) {
            model.put("table", table);
            // Controller
            String controllerTargetPath =
                webTargetPath + "/src/main/java" + File.separator + webPackagePath + File.separator
                    + table.getLowerCamelName() + File.separator + "controller";
            String controllerTargetName = table.getClassName() + "Controller.java";
            executeFreemarker(webModuleName, model,
                new PathInfo(webTemplatePath, relativeWebTemplatePath, "Controller.ftl", controllerTargetPath,
                    controllerTargetName), type);
        }
    }

    private static void genServerModule(final String serverModuleName, final Map<String, Object> model,
        final String serverTemplatePath, final String relativeServerTemlatePath, final String serverTargetPath,
        final DbInfo dbInfo,
        final String serverPackagePath, final String type) throws IOException {
        log.info("Generate " + serverModuleName + " Module : " + serverTargetPath + "/src/test/java");
        FileUtils.createDir(serverTargetPath + "/src/test/java", false);
        log.info("==> success");

        log.info("Generate " + serverModuleName + " Module : " + serverTargetPath + "/src/main/java");
        FileUtils.createDir(serverTargetPath + "/src/main/java", false);
        log.info("==> success");

        log.info("Generate " + serverModuleName + " Module : " + serverTargetPath + "/src/main/resources");
        FileUtils.createDir(serverTargetPath + "/src/main/resources", false);
        log.info("==> success");

        copyDirectory(serverModuleName, serverTemplatePath + "/i18n", serverTargetPath + "/src/main/resources/i18n");

        executeFreemarker(serverModuleName, model,
            new PathInfo(serverTemplatePath, relativeServerTemlatePath, "pom.ftl", serverTargetPath, "pom.xml"), type);
        executeFreemarker(serverModuleName, model,
            new PathInfo(serverTemplatePath, relativeServerTemlatePath, "custom_logback.ftl",
                serverTargetPath + "/src/main/resources",
                "custom_logback.xml"), type);
        executeFreemarker(serverModuleName, model,
            new PathInfo(serverTemplatePath, relativeServerTemlatePath, "application.ftl",
                serverTargetPath + "/src/main/resources",
                "application.yaml"), type);
        executeFreemarker(serverModuleName, model,
            new PathInfo(serverTemplatePath, relativeServerTemlatePath, "BootStrap.ftl",
                serverTargetPath + "/src/main/java" + File.separator + serverPackagePath, "BootStrap.java"), type);
    }
}
