package com.bookMyShow.bookmyshow.userfilter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.bookMyShow.bookmyshow.userfilter.UserContext.getUserThreadId;
import static com.bookMyShow.bookmyshow.userfilter.UserContext.setUserThreadId;

@Component
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        setUserThreadId(httpServletRequest.getHeader("userId"));

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Autowired
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
