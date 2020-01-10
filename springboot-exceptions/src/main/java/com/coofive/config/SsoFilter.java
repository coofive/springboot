package com.coofive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author coofive
 */
@Component("ssoFilter")
@WebFilter(filterName = "ssoFilter", urlPatterns = "/*")
public class SsoFilter implements Filter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        resolver.resolveException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, null, new RuntimeException("测试"));
    }

    @Override
    public void destroy() {

    }
}
