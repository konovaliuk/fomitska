package ua.training.controller;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private FilterConfig filterConfig;
    private String code;

    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
        code = fConfig.getInitParameter("encoding");
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                        FilterChain chain) throws IOException, ServletException {
        String codeRequest= request.getCharacterEncoding();
        if(code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    chain.doFilter(request, response);
}
    public void destroy() {
        code= null;
    }
}
