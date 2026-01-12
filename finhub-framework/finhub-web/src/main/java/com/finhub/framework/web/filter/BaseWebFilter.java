package com.finhub.framework.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * BaseWebFilter 给所有的 Web Filter 增加父类，方便统一管理
 * <p>
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/5/12 8:01 下午
 */
@Slf4j
public abstract class BaseWebFilter implements Filter {

    private final List<String> pathUrlPatterns = new ArrayList<>();

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        if (this.matches(path)) {
            filterChain.doFilter(request, response);
        }
    }

    private boolean matches(String requestPath) {
        for (String pattern : pathUrlPatterns) {
            if (pathMatcher.match(pattern, requestPath)) {
                return true;
            }
        }
        return false;
    }
}
