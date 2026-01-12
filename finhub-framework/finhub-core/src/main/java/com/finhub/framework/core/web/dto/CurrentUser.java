package com.finhub.framework.core.web.dto;

import com.google.common.collect.Maps;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 自定义 Authentication 对象，使得 Subject 除了携带用户的登录名外还可以携带更多信息
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/08/21 18:29
 */
@Data
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;

    private String id;

    private String loginName;

    private String name;

    private String code;

    private String roleId;

    private String roleName;

    private String roleCode;

    private String mobile;

    private Integer isTest = 0;
    private Integer dataSecurityVal = 0;

    private Set<String> roleIdSet;
    private Set<String> roleCodeSet;

    private Set<String> permissionIdSet;
    private Set<String> permissionCodeSet;

    private final Map<String, Serializable> CONTEXT_MAP = Maps.newHashMap();

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

    public void put(String key, Serializable value) {
        CONTEXT_MAP.put(key, value);
    }

    public Serializable get(String key) {
        return CONTEXT_MAP.getOrDefault(key, null);
    }
}
