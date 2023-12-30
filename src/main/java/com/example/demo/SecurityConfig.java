package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@Order(1)
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new  CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return daoAuthenticationProvider;
    }
	
		
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		 http
	        .authorizeRequests()
	            .requestMatchers("/").permitAll()
	            .requestMatchers("/login/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	            .and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/dashboard")
			.permitAll()
			.and()
			.logout().logoutUrl("/logout")
			.logoutSuccessUrl("/");
		return http.build();
	}
	


}
//http.csrf().disable().securityMatcher("/admin/**")
//.authorizeHttpRequests().anyRequest()
//.hasAuthority("ADMIN")