package com.p10.Inventory_Management.config;

import com.p10.Inventory_Management.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth->auth
                        .antMatchers(HttpMethod.GET,"/api/articles/**").hasRole("admin")
                        .antMatchers(HttpMethod.POST,"/api/articles/**").hasRole("admin")
                        .antMatchers(HttpMethod.PUT,"/api/articles/**").hasRole("admin")
                        .antMatchers(HttpMethod.DELETE,"/api/articles/**").hasRole("admin")
                )
                .httpBasic()
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // NoOpPasswordEncoder used to bypass password checks
    }


}
