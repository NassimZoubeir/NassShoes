package com.ensup.NassShoes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(expressionInterceptUrlRegistry ->
            expressionInterceptUrlRegistry
              .requestMatchers("/", "/accueil", "/catalogue", "/creer-compte", "/creer-compte-validation", "/connexion","/deconnexion","/connexion-validation","/css/**", "/images/**", "/assets/**").permitAll()
              .anyRequest().authenticated()) // Restrict all other routes
          .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}

