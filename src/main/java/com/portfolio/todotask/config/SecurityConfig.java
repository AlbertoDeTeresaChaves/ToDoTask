package com.portfolio.todotask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.portfolio.todotask.security.AuthEntryPointJwt;
import com.portfolio.todotask.security.AuthTokenFilter;
import com.portfolio.todotask.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	private AuthEntryPointJwt unathorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration
	) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .exceptionHandling(exceptionHandling ->
            		exceptionHandling.authenticationEntryPoint(unathorizedHandler)
            		)
            .sessionManagement(sessionManagement->
            		sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            		)
            .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers("/api/auth/**", "/api/test/all").permitAll()
                .anyRequest().authenticated()
            );
        http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
