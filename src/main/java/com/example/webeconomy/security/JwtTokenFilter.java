package com.example.webeconomy.security;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.webeconomy.services.AuthService;

@Component
public class JwtTokenFilter extends OncePerRequestFilter{
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String phoneNumber = null;
        String jwt = null;
        if (authorizationHeader !=null && authorizationHeader.startsWith("Bearer")){
            jwt = authorizationHeader.substring(7);
            phoneNumber = jwtTokenProvider.getPhoneNumberFromJwtToken(jwt);
        }
        if (phoneNumber!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.authService.loadUserByUsername(phoneNumber);
            if(jwtTokenProvider.validateJwtToken(jwt)){
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
      }
}
