package com.ensup.NassShoes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ensup.NassShoes.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
    private CustomUserDetailsService userDetailsService;
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	   http
           .authorizeHttpRequests(authz -> authz
               .requestMatchers("/", "/accueil", "/catalogue", "/creer-compte", "/creer-compte-validation", "/connexion", "/css/**", "/images/**", "/assets/**").permitAll()
               .requestMatchers("/creer-chaussures").hasRole("ADMINISTRATEUR")
               .requestMatchers("/afficher-panier").permitAll() 
               .anyRequest().authenticated()
           )
           .formLogin(form -> form
               .loginPage("/connexion")
               .loginProcessingUrl("/connexion-validation")
               .defaultSuccessUrl("/accueil", true)
               .permitAll()
           )
           .logout(logout -> logout
               .logoutUrl("/deconnexion")
               .logoutSuccessUrl("/accueil")
           )
           .userDetailsService(userDetailsService)
           .csrf(AbstractHttpConfigurer::disable);

       return http.build();
   }
}

