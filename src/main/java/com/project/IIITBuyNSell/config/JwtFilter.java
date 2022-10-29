package com.project.IIITBuyNSell.config;

import com.project.IIITBuyNSell.service.CustomUserDetailsService;
import com.project.IIITBuyNSell.utils.JwtUtils;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authorization != null && authorization.startsWith("Bearer")) {
            token = authorization.substring(7);
            try {
                username = jwtUtils.getUsernameFromToken(token);
            } catch (MalformedJwtException e) {
                log.error("invalid jwt token");
            } catch (Exception e) {
                log.error("exception occured : " + e);
            }
        } else {
            log.info("Jwt token does not begin with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (Boolean.TRUE.equals(this.jwtUtils.validateToken(token, userDetails))) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                log.info("Invalid token");
            }
        } else {
            log.error("Either username is null or context is not null");
        }
        filterChain.doFilter(request, response);
    }
}
