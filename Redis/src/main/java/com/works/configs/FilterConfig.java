package com.works.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        String url = req.getRequestURI();
        if ( url.contains("/login") || url.contains("/logout") ) {
            chain.doFilter(req, res);
        }else {
            boolean status = req.getSession().getAttribute("email") == null;
            if ( status ) {
                String data = "{ \"status\": false, \"messsage\": \"Plase Login\" }";
                res.setContentType("application/json");
                res.getWriter().println(data);
            }else {
                String stData = (String) req.getSession().getAttribute("email");
                Admin admin = objectMapper.readValue(stData, Admin.class);
                System.out.println( admin );
                chain.doFilter(req, res);
            }
        }


    }

}
