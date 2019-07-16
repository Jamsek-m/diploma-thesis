package com.mjamsek.metrics.api.v1.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PoweredByFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.addHeader("X-Powered-By", "MetricsMonitor v1");
            
            chain.doFilter(request, resp);
        } else {
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
    
    }
}
