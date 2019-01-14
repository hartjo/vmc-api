package com.restapi.hartjo.authentications;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class JwtTokenFilter extends GenericFilterBean {

  private JwtTokenProvider jwtTokenProvider;
  
  @Autowired
  AuthenticationManager authManager;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
      throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
    try {
    	
      if (token != null && jwtTokenProvider.validateToken(token)) {
    	  
    	  System.out.println(token);
    	  UsernamePasswordAuthenticationToken authReq
          = new UsernamePasswordAuthenticationToken("username","id");
        Authentication auth = authManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
    	  
      }
    } catch (Exception ex) {
      return;
    }

    filterChain.doFilter(req, res);
  }

}
