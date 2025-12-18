package com.example.demo.config;

import com.example.demo.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if (path.startsWith("/auth/") || path.equals("/hello-servlet") || 
            path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        final String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        String token = authHeader.substring(7);
        
        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractUsername(token);
            Long userId = jwtUtil.extractUserId(token);
            String role = jwtUtil.extractRole(token);
            
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                );
            
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}