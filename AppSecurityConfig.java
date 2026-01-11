package com.codeur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService detailsService,PasswordEncoder encoder) {		
		DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider(detailsService);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->
		           auth.requestMatchers("/login","/register").permitAll()
				  .anyRequest().authenticated()
				);
		return http.build();
		
	}
	
	
	

	
}
