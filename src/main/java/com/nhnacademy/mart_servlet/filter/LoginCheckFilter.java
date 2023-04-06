package com.nhnacademy.mart_servlet.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "exclude-urls", value = "/login.do\n/logout.do\n/loginForm.html")
})
public class LoginCheckFilter implements Filter {
    private static final Set<String> EXCLUDE_URLS = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        String[] initParameter = filterConfig.getInitParameter("exclude-urls").split(System.lineSeparator());
        Arrays.stream(initParameter)
                .map(String::trim)
                .forEach(EXCLUDE_URLS::add);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if (!EXCLUDE_URLS.contains(httpServletRequest.getRequestURI())) {
            HttpSession session = httpServletRequest.getSession(false);

            if (Objects.isNull(session)) {
                ((HttpServletResponse) servletResponse).sendRedirect("login.do");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
