package com.finhub.codegen;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.codegen.util.PinYinUtils;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.codegen.util.TemplateUtils;

import cn.hutool.core.util.StrUtil;
import com.finhub.codegen.model.Dic;
import com.finhub.codegen.model.DicType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author Mickey
 * @version 1.0
 * @since 2017/4/8 下午3:00
 */
public class DbInitMain {
    public static void main(String[] args) {
        initDictionary();
//        initResourcesAndRole();
//        genDictionaryKit();
//        randomMember();
    }

    private static final List<String> MEMBERS = Lists.newArrayList("陶明凯", "姚慧欣", "李小波", "凌晓斌", "王振兴", "许晨熙", "王秀虎", "郭薇", "高羽", "徐宁", "吴爽", "陈静");

    private static final String DICTIONARY_PARAMS =
        "用户状态:[1000=启用,1001=停用]|" +
            "机构类型:[2000=其他]|" +
            "日志类型:3000=登录登出[操作类型$3001=登录,3002=登出]&4000=启用停用[操作类型$4001=启用,4002=停用]";

    private static void initDictionary() {
        List<String> dictionaryParams = StrUtil.split(DICTIONARY_PARAMS, "|");

        // 主键ID从1开始递增
        int id = 1;
        for (String dictionary : dictionaryParams) {
            List<String> dictionaryParts = StrUtil.split(dictionary, ":");
            String subDictionary;
            String parentDictionary;
            // 判断是否符合XXXX:0000=YYYY[1111=AAAA,2222=BBBB]
            if (StrUtil.containsIgnoreCase(dictionaryParts.get(1), "[")) {
                if (StrUtil.startWith(dictionaryParts.get(1), "[", false)) {
                    // 处理XXXX:[1111=AAAA,2222=BBBB]，无父字典情况
                    subDictionary = StrUtil.sub(dictionaryParts.get(1), 1, dictionaryParts.get(1).length() - 1);

                    // 插入子字典
                    List<String> subDictionaryParams = StrUtil.split(subDictionary, ",");
                    for (String subDictionaryParam : subDictionaryParams) {
                        List<String> parts = StrUtil.split(subDictionaryParam, "=");
                        System.out.println(
                            StrUtil.format("INSERT INTO `bd_dictionary` " +
                                    "(`id`, `name`, `code`, `value`, `type`, `typeName`, `sort`, `pid`, `pcode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                    "VALUES " +
                                    "({}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                                id++, parts.get(1), PinYinUtils.getPinYinHeadChar(parts.get(1)).toUpperCase(), Integer.valueOf(parts.get(0).trim()), PinYinUtils.getPinYinHeadChar(dictionaryParts.get(0)).toUpperCase(), dictionaryParts.get(0), 0, 0, StrConstants.S_EMPTY, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));
                    }
                } else {
                    // 处理XXXX:0000=YYYY[EEEE$1111=AAAA,2222=BBBB]&1111=ZZZZ[FFFF$1111=CCCC,2222=DDDD]，有父字典情况
                    List<String> subDictionaryParts = StrUtil.split(dictionaryParts.get(1), "&");
                    for (String part : subDictionaryParts) {
                        String dictionaryType = PinYinUtils.getPinYinHeadChar(dictionaryParts.get(0)).toUpperCase();
                        String dictionaryTypeName = dictionaryParts.get(0);
                        parentDictionary = StrUtil.subPre(part, part.indexOf("["));
                        subDictionary = StrUtil.sub(part, part.indexOf("[") + 1, part.length() - 1);
                        if (subDictionary.contains("$")) {
                            dictionaryType = PinYinUtils.getPinYinHeadChar(
                                StrUtil.subPre(subDictionary, subDictionary.indexOf("$"))).toUpperCase();
                            dictionaryTypeName = StrUtil.subPre(subDictionary, subDictionary.indexOf("$"));
                            subDictionary = StrUtil.sub(subDictionary, subDictionary.indexOf("$") + 1, subDictionary.length());
                        }

                        // 优先插入父字典
                        List<String> parentParts = StrUtil.split(parentDictionary, "=");

                        int pid = id++;
                        System.out.println(
                            StrUtil.format("INSERT INTO `bd_dictionary` " +
                                    "(`id`, `name`, `code`, `value`, `type`, `typeName`, `sort`, `pid`, `pcode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                    "VALUES " +
                                    "({}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                                pid, parentParts.get(1), PinYinUtils.getPinYinHeadChar(parentParts.get(1)).toUpperCase(), Integer.valueOf(parentParts.get(0).trim()), PinYinUtils.getPinYinHeadChar(dictionaryParts.get(0)).toUpperCase(), dictionaryParts.get(0), 0, 0, StrConstants.S_EMPTY, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

                        // 插入子字典
                        List<String> subDictionaryParams = StrUtil.split(subDictionary, ",");
                        for (String subDictionaryParam : subDictionaryParams) {
                            List<String> subParts = StrUtil.split(subDictionaryParam, "=");
                            System.out.println(
                                StrUtil.format("INSERT INTO `bd_dictionary` " +
                                        "(`id`, `name`, `code`, `value`, `type`, `typeName`, `sort`, `pid`, `pcode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                        "VALUES " +
                                        "({}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                                    id++, subParts.get(1), PinYinUtils.getPinYinHeadChar(subParts.get(1)).toUpperCase(), Integer.valueOf(subParts.get(0).trim()), dictionaryType, dictionaryTypeName, 0, pid, StrConstants.S_EMPTY, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));
                        }
                    }
                }
            }
        }
    }

    private static void genDictionaryKit() {
        Map<String, List<DicType>> model = Maps.newHashMap();
        Map<String, DicType> dicTypeMap = Maps.newHashMap();
        List<DicType> dicTypes = Lists.newArrayList();
        model.put("dicTypes", dicTypes);

        List<String> dictionaryParams = StrUtil.split(DICTIONARY_PARAMS, "|");

        for (String dictionary : dictionaryParams) {
            List<String> dictionaryParts = StrUtil.split(dictionary, ":");
            DicType dicType = new DicType();
            dicType.setCode(PinYinUtils.getPinYinHeadChar(dictionaryParts.get(0)).toUpperCase());
            dicType.setName(dictionaryParts.get(0));
            dicTypes.add(dicType);
            dicTypeMap.put(dicType.getName(), dicType);

            String subDictionary;
            // 判断是否符合XXXX:0000=YYYY[1111=AAAA,2222=BBBB]
            if (StrUtil.containsIgnoreCase(dictionaryParts.get(1), "[")) {
                if (StrUtil.startWith(dictionaryParts.get(1), "[", false)) {
                    // 处理XXXX:[1111=AAAA,2222=BBBB]，无父字典情况
                    subDictionary = StrUtil.sub(dictionaryParts.get(1), 1, dictionaryParts.get(1).length() - 1);

                    // 插入子字典
                    List<String> subDictionaryParams = StrUtil.split(subDictionary, ",");
                    List<Dic> subDics = Lists.newArrayList();
                    if (Func.isNotEmpty(dicType.getDics())) {
                        subDics = dicType.getDics();
                    } else {
                        dicType.setDics(subDics);
                    }

                    for (String subDictionaryParam : subDictionaryParams) {
                        List<String> parts = StrUtil.split(subDictionaryParam, "=");
                        Dic dic = new Dic();
                        Map<String, String> params = Maps.newHashMap();
                        params.put("\\(", "_");
                        params.put("\\)", StrConstants.S_EMPTY);

                        dic.setName(parts.get(1));
                        // dic.setCode(StrUtil.replace(PinYinUtils.getPinYinHeadChar(parts[1]).toUpperCase(), params));
                        dic.setOrigCode(PinYinUtils.getPinYinHeadChar(parts.get(1)).toUpperCase());
                        subDics.add(dic);
                    }
                } else {
                    // 处理XXXX:0000=YYYY[EEEE$1111=AAAA,2222=BBBB]&1111=ZZZZ[FFFF$1111=CCCC,2222=DDDD]，有父字典情况
                    List<Dic> subDics = Lists.newArrayList();
                    if (Func.isNotEmpty(dicType.getDics())) {
                        subDics = dicType.getDics();
                    } else {
                        dicType.setDics(subDics);
                    }

                    List<String> subDictionaryParts = StrUtil.split(dictionaryParts.get(1), "&");
                    for (String part : subDictionaryParts) {
                        String dictionaryType = PinYinUtils.getPinYinHeadChar(dictionaryParts.get(0)).toUpperCase();
                        String dictionaryTypeName = dictionaryParts.get(0);
                        subDictionary = StrUtil.sub(part, part.indexOf("[") + 1, part.length() - 1);
                        if (subDictionary.contains("$")) {
                            dictionaryType = PinYinUtils.getPinYinHeadChar(
                                StrUtil.subPre(subDictionary, subDictionary.indexOf("$"))).toUpperCase();
                            dictionaryTypeName = StrUtil.subPre(subDictionary, subDictionary.indexOf("$"));
                            subDictionary = StrUtil.sub(subDictionary, subDictionary.indexOf("$") + 1, subDictionary.length());
                        }

                        String parentDictionary = StrUtil.subPre(part, part.indexOf("["));
                        List<String> parentParts = StrUtil.split(parentDictionary, "=");

                        Dic dic = new Dic();
                        Map<String, String> params = Maps.newHashMap();
                        params.put("\\(", "_");
                        params.put("\\)", StrConstants.S_EMPTY);

                        dic.setName(parentParts.get(1));
                        // dic.setCode(
                        //    StrUtil.replace(PinYinUtils.getPinYinHeadChar(parentParts.get(1)).toUpperCase(), params));
                        dic.setOrigCode(PinYinUtils.getPinYinHeadChar(parentParts.get(1)).toUpperCase());
                        subDics.add(dic);

                        DicType subDicType;
                        if (dicTypeMap.containsKey(dictionaryTypeName)) {
                            subDicType = dicTypeMap.get(dictionaryTypeName);
                        } else {
                            subDicType = new DicType();
                            dicTypes.add(subDicType);
                            dicTypeMap.put(dictionaryTypeName, subDicType);
                        }

                        subDicType.setName(dictionaryTypeName);
                        subDicType.setCode(dictionaryType);

                        // 插入子字典
                        List<String> subDictionaryParams = StrUtil.split(subDictionary, ",");
                        List<Dic> subDictionarys = Lists.newArrayList();
                        if (Func.isNotEmpty(subDicType.getDics())) {
                            subDictionarys = subDicType.getDics();
                        } else {
                            subDicType.setDics(subDictionarys);
                        }

                        for (String subDictionaryParam : subDictionaryParams) {
                            List<String> subParts = StrUtil.split(subDictionaryParam, "=");

                            Dic subDic = new Dic();
                            Map<String, String> subParams = Maps.newHashMap();
                            subParams.put("\\(", "_");
                            subParams.put("\\)", StrConstants.S_EMPTY);

                            subDic.setName(subParts.get(1));
                            // subDic.setCode(
                            //    StrUtil.replace(PinYinUtils.getPinYinHeadChar(subParts.get(1)).toUpperCase(), subParams));
                            subDic.setOrigCode(PinYinUtils.getPinYinHeadChar(subParts.get(1)).toUpperCase());
                            subDictionarys.add(subDic);
                        }
                    }
                }
            }
        }

        TemplateUtils.executeFreemarker("/Users/TaoBangren/git@osc/easy-code/jeasy-code-gen/src/main/resources","/", "DictionaryKit.ftl", CharsetUtils.DEFAULT_ENCODE, model, "/Users/TaoBangren/git@osc/easy-code/jeasy-code-gen/src/main/java/com/jeasy", "DictionaryKit.java","file");
    }

    private static final String RESOURCE_PARAMS =
        "用户管理(ios-people)=" +
            "{人员管理(ios-body&/user)[查询-查看-新增-编辑-删除-角色配置-机构配置]};" +
            "{角色管理(ios-person&/role)[查询-查看-新增-编辑-删除-权限配置]};" +
            "{组织机构(ios-people&/organization)[查询-查看-新增-编辑-删除]};" +
            "{菜单资源(android-menu&/resource)[查询-查看-新增-编辑-删除]}|" +
            "基础数据(settings)=" +
            "{公共码表(ios-book&/dictionary)[查询-查看-新增-编辑-删除]};" +
            "{文件管理(ios-folder&/fileAttach)[查询-查看-新增-编辑-删除]}|" +
            "代码平台(code-working)=" +
            "{接口文档(usb&/api)[查询-查看-新增-编辑-删除]};" +
            "{代码生成(code-download&/code)[查询-查看-新增-编辑-删除]}|" +
            "日志监控(monitor)=" +
            "{操作日志(ios-paper&/log)[查询-查看-新增-编辑-删除]};" +
            "{数据监控(ios-analytics&/druid)[查询-查看-新增-编辑-删除]};" +
            "{接口监控(usb&/monitor)[查询-查看-新增-编辑-删除]}";

    private static void initResourcesAndRole() {
        List<String> menus = StrUtil.split(RESOURCE_PARAMS, "|");

        int menuId = 1;
        int roleResourceId = 1;
        for (String menu : menus) {
            String[] menuParts = menu.split("=");
            String menuName = menuParts[0];
            String icon = menuName.substring(menuName.lastIndexOf("(") + 1, menuName.lastIndexOf(")"));
            menuName = menuName.substring(0, menuName.lastIndexOf("("));

            // 处理一级菜单
            System.out.println(
                StrUtil.format("INSERT INTO `su_resource` " +
                        "(`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `isMenu`, `isLeaf`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                        "VALUES " +
                        "({}, '{}', '{}', '{}', '{}', '{}', {}, {}, {}, {}, {}, {}, '{}', {}, {}, '{}', {}, {});",
                    menuId, menuName, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase(), StrConstants.S_EMPTY, icon, StrConstants.S_EMPTY, 0, 0, 1, 0, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

            System.out.println(StrUtil.format("INSERT INTO `su_role_resource` (`id`, `roleId`, `roleName`, `roleCode`, `resourceId`, `resourceName`, `resourceCode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                    "VALUES " +
                    "({}, {}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                roleResourceId++, 1, "超级管理员", "CJGLY", menuId, menuName, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase(), System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

            int pid = menuId++;
            String[] subMenus = menuParts[1].split(";");
            for (String subMenuInfo : subMenus) {

                String[] subMenuParts = subMenuInfo.split("\\[");
                String subMenuName = subMenuParts[0].substring(1);
                icon = subMenuName.substring(subMenuName.lastIndexOf("(") + 1, subMenuName.lastIndexOf("&"));
                String url = subMenuName.substring(subMenuName.lastIndexOf("&") + 1, subMenuName.lastIndexOf(")"));
                subMenuName = subMenuName.substring(0, subMenuName.lastIndexOf("("));

                // 处理二级菜单
                System.out.println(
                    StrUtil.format("INSERT INTO `su_resource` " +
                            "(`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `isMenu`, `isLeaf`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                            "VALUES " +
                            "({}, '{}', '{}', '{}', '{}', '{}', {}, {}, {}, {}, {}, {}, '{}', {}, {}, '{}', {}, {});",
                        menuId, subMenuName, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase(), url, icon, StrConstants.S_EMPTY, pid, 0, 1, 1, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

                System.out.println(StrUtil.format("INSERT INTO `su_role_resource` (`id`, `roleId`, `roleName`, `roleCode`, `resourceId`, `resourceName`, `resourceCode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                        "VALUES " +
                        "({}, {}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                    roleResourceId++, 1, "超级管理员", "CJGLY", menuId, subMenuName, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase(), System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

                int parentMenuId = menuId++;
                String[] tabInfos = subMenuParts[1].substring(0, subMenuParts[1].lastIndexOf("]")).split(",");
                for (String tabInfo : tabInfos) {

                    List<String> options;
                    if (tabInfo.contains(":")) {
                        String[] tabParts = tabInfo.split(":");
                        String tabName = tabParts[0];
                        // 处理二级菜单下的选项卡
                        System.out.println(
                            StrUtil.format("INSERT INTO `su_resource` " +
                                    "(`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `isMenu`, `isLeaf`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                    "VALUES " +
                                    "({}, '{}', '{}', '{}', '{}', '{}', {}, {}, {}, {}, {}, {}, '{}', {}, {}, '{}', {}, {});",
                                menuId, tabName, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(tabName).toUpperCase(), StrConstants.S_EMPTY, StrConstants.S_EMPTY, StrConstants.S_EMPTY, parentMenuId, 0, 0, 0, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

                        System.out.println(StrUtil.format("INSERT INTO `su_role_resource` (`id`, `roleId`, `roleName`, `roleCode`, `resourceId`, `resourceName`, `resourceCode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                "VALUES " +
                                "({}, {}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                            roleResourceId++, 1, "超级管理员", "CJGLY", menuId, tabName, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(tabName).toUpperCase(), System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

                        int optionParentMenuId = menuId++;
                        // 处理选项卡下的操作按钮
                        options = StrUtil.split(tabParts[1], "-");
                        for (String option : options) {
                            System.out.println(
                                StrUtil.format("INSERT INTO `su_resource` " +
                                        "(`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `isMenu`, `isLeaf`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                        "VALUES " +
                                        "({}, '{}', '{}', '{}', '{}', '{}', {}, {}, {}, {}, {}, {}, '{}', {}, {}, '{}', {}, {});",
                                    menuId, option, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(tabName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(option).toUpperCase(), StrConstants.S_EMPTY, StrConstants.S_EMPTY, StrConstants.S_EMPTY, optionParentMenuId, 0, 0, 1, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

                            System.out.println(StrUtil.format("INSERT INTO `su_role_resource` (`id`, `roleId`, `roleName`, `roleCode`, `resourceId`, `resourceName`, `resourceCode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                    "VALUES " +
                                    "({}, {}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                                roleResourceId++, 1, "超级管理员", "CJGLY", menuId++, option, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(tabName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(option).toUpperCase(), System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));
                        }
                    } else {
                        // 处理二级菜单下的操作按钮
                        options = StrUtil.split(tabInfo, "-");

                        for (String option : options) {
                            System.out.println(
                                StrUtil.format("INSERT INTO `su_resource` " +
                                        "(`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `isMenu`, `isLeaf`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                        "VALUES " +
                                        "({}, '{}', '{}', '{}', '{}', '{}', {}, {}, {}, {}, {}, {}, '{}', {}, {}, '{}', {}, {});",
                                    menuId, option, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(option).toUpperCase(), StrConstants.S_EMPTY, StrConstants.S_EMPTY, StrConstants.S_EMPTY, parentMenuId, 0, 0, 1, System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));

                            System.out.println(StrUtil.format("INSERT INTO `su_role_resource` (`id`, `roleId`, `roleName`, `roleCode`, `resourceId`, `resourceName`, `resourceCode`, `createAt`, `createBy`, `createName`, `updateAt`, `updateBy`, `updateName`, `isDel`, `isTest`) " +
                                    "VALUES " +
                                    "({}, {}, '{}', '{}', {}, '{}', '{}', {}, {}, '{}', {}, {}, '{}', {}, {});",
                                roleResourceId++, 1, "超级管理员", "CJGLY", menuId++, option, PinYinUtils.getPinYinHeadChar(menuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(subMenuName).toUpperCase() + ":" + PinYinUtils.getPinYinHeadChar(option).toUpperCase(), System.currentTimeMillis(), 0, "SYSTEM", System.currentTimeMillis(), 0, "SYSTEM", 0, 0));
                        }
                    }
                }
            }
        }
    }
}
