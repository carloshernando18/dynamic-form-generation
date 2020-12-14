package com.dynamicformgeneration.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    HttpServletResponse httpServeltResponse = (HttpServletResponse) response;

    httpServeltResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpServeltResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    httpServeltResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
    httpServeltResponse.setHeader("Access-Control-Max-Age", "5000");

    if ("OPTIONS".equals(((HttpServletRequest) request).getMethod())) {
      httpServeltResponse.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(request, httpServeltResponse);
    }
  }

  

}
