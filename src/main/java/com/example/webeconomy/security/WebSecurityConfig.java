package com.example.webeconomy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import com.example.webeconomy.services.AuthService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.webeconomy.constants.Erole;
import com.example.webeconomy.services.AuthService;
import com.example.webeconomy.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.DELETE;;

@Deprecated
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public  PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    AuthService authService;
    @Autowired
    JwtEntryPoint jwtEntryPointJwt;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity.authorizeRequests().antMatchers("/customer/login").permitAll();
        // httpSecurity.authorizeRequests().antMatchers("/").permitAll();
        httpSecurity.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtEntryPointJwt).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers(POST,"/product").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(PUT,"/product").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(DELETE,"/product").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(PUT,"/order/**").permitAll()
            .antMatchers("/customer/**").permitAll()
            .antMatchers("/product/**").permitAll()
            .antMatchers("/category/**").permitAll()
            .antMatchers("/account/**").permitAll()
            .antMatchers("/order/**").hasAnyAuthority("ROLE_ADMIN")
            .anyRequest().authenticated();
            httpSecurity.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}