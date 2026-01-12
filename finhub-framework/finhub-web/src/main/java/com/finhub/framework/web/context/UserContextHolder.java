package com.finhub.framework.web.context;

import com.finhub.framework.core.web.dto.CurrentUser;

import cn.hutool.extra.spring.SpringUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 当前用户上下文 Holder 此类不再对外提供已废弃，请使用 {@link com.fenbeitong.finhub.auth.UserAuthHolder} 代替
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2021/08/21 10:16
 */
@Deprecated
public interface UserContextHolder {

    /**
     * 获取当前实例
     *
     * @return
     */
    static UserContextHolder me() {
        try {
            return SpringUtil.getBean(UserContextHolder.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前用户
     *
     * @param request
     * @param response
     * @return
     */
    CurrentUser getCurrentUser(HttpServletRequest request, HttpServletResponse response);
}
