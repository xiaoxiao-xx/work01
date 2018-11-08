package com.wh.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shawn
 * @create 2018-11-08 9:42
 **/
@Component
@WebFilter(urlPatterns = "/*.html",filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getServletPath();
        String  name = (String) req.getSession().getAttribute("loginName");
        if ("/login.html".equals(path)|| path.endsWith("css")|| path.endsWith("js")|| path.endsWith("ico")|| path.endsWith("png")|| path.endsWith("gif")|| path.endsWith("ajax")) {
            chain.doFilter(request, response);
        }else {
            if (name == null) {
                resp.sendRedirect(req.getContextPath()+"/login.html");
            }else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
