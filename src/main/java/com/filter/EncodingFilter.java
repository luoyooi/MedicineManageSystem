package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-06-01 12:32
 */
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getRequestURI().contains(".css") || request.getRequestURI().contains(".js"))
        {
            chain.doFilter(request, response);
        }
        else
        {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            resp.setContentType("text/html;charset=UTF-8");
            chain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {
    }

}
