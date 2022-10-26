package com.example.webeconomy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import com.example.webeconomy.services.AuthService;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.example.webeconomy.services.impl.AuthServiceImpl;

// @Deprecated
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // private AuthServiceImpl authServiceImpl;
    // @Bean
    // public PasswordEncoder encoder(){
    //     return new BCryptPasswordEncoder();
    // }
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    //     auth.userDetailsService(authServiceImpl).passwordEncoder(encoder());
    // }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity.authorizeRequests().antMatchers("/customer/login").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
        httpSecurity.cors().and().csrf().disable();
    }
}