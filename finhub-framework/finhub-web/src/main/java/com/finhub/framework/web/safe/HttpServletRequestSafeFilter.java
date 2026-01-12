package com.finhub.framework.web.safe;

import com.finhub.framework.core.thread.ThreadLocalUtils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * 安全的Filter(过滤SQL和XSS)
 *
 * @author Mickey
 * @version 1.0
 * @since 15-8-4 下午4:45
 */
@Slf4j
public class HttpServletRequestSafeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            ThreadLocalUtils.removeAll();

            chain.doFilter(new HttpServletRequestSafeWrapper((HttpServletRequest) request), response);
        } finally {
            ThreadLocalUtils.removeAll();
        }
    }
}
