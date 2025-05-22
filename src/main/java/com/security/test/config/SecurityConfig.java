package com.security.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.test.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	UserDetailsService userDetailsService() {
		return new UserInfoUserDetailsService();
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()) // Completely disable CSRF
				.authorizeHttpRequests(auth -> auth.requestMatchers("/welcome", "/users/**", "/addUser").permitAll() // Allow
																														// unrestricted
																														// access
						.requestMatchers("/products/**").authenticated().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).build();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(encoder());
		return authenticationProvider;
	}

}


/*
@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	return http
           .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-ui/**")) // Ignore CSRF for H2 console
           .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Allow frames for H2 console
           .authorizeHttpRequests(auth -> auth
                   .requestMatchers("/h2-ui/**", "/welcome", "/users/**").permitAll() // Public access
                   .requestMatchers("/products/**").authenticated() // Require authentication
                   .anyRequest().authenticated() // Secure all other endpoints
           )
           .formLogin(Customizer.withDefaults()) // Enable default form login
           .build();
}
*/
