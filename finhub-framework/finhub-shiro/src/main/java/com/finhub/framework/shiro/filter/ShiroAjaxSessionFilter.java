package com.finhub.framework.shiro.filter;

import com.finhub.framework.core.web.RequestUtils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;


/**
 * ajax shiro session 超时统一处理
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 */
public class ShiroAjaxSessionFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        if (RequestUtils.isAjaxRequest(req)) {
            HttpServletResponse res = WebUtils.toHttp(response);
            // 采用res.sendError(401);在Easyui中会处理掉error，$.ajaxSetup中监听不到
            res.setHeader("oauthstatus", "401");
            return false;
        }
        return super.onAccessDenied(request, response);
    }
}
