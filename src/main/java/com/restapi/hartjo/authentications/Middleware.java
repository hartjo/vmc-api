package com.restapi.hartjo.authentications;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author jan.llarenas
 *
 * This Class will intercept any HTTP Request
 */

@Component
public class Middleware extends OncePerRequestFilter{


	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // your code
        filterChain.doFilter(request, response);
    }
	
}
