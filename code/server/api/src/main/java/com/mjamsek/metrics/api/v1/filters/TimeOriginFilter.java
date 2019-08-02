package com.mjamsek.metrics.api.v1.filters;

import com.mjamsek.metrics.config.MonitorConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ApplicationScoped
public class TimeOriginFilter implements Filter {
    
    @Inject
    private MonitorConfig monitorConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String requestOrigin = ((HttpServletRequest) servletRequest).getHeader("Origin");
            
            if (requestOrigin != null) {
                String allowedOrigin = this.handleOrigin(requestOrigin);
                if (allowedOrigin != null) {
                    response.setHeader("Timing-Allow-Origin", allowedOrigin);
                }
            }
            chain.doFilter(servletRequest, response);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    private String handleOrigin(String origin) {
        if (monitorConfig.getAllowedTimeOrigins().equalsIgnoreCase("*")) {
            return origin;
        }
        String[] allowedOrigins = monitorConfig.getAllowedTimeOrigins().split(",");
        for (String allowedOrigin : allowedOrigins) {
            if (allowedOrigin.equalsIgnoreCase(origin)) {
                return origin;
            }
        }
        return null;
    }
}
