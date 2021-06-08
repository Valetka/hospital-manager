package com.hospital_manager.controllers.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private String encoding;
    private ServletContext servletContext;

    private static final String CHAR_ENCODING = "characterEncoding";
    private static final String SET = "charset was set";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(CHAR_ENCODING);
        servletContext = filterConfig.getServletContext();

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        servletContext.log(SET);
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
