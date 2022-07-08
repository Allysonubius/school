package com.example.project.school.Config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.project.school.Service.TokenService;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtRequestFilterConfig extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
            final String REQUEST_TOKEN_HEADER = request.getHeader("Authorization");

            String email = null;
            String token = null;

            if(REQUEST_TOKEN_HEADER != null && REQUEST_TOKEN_HEADER.startsWith("Bearer")){
                token = REQUEST_TOKEN_HEADER.substring(7);
                try {
                    email = this.tokenService.getUsernameFromToken(token);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");;
                }catch(ExpiredJwtException e){
                    System.out.println("JWT Token has expired");
                }
            }

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.tokenService.loadUserByUsername(email);
                if(tokenService.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        
    }
    
}
