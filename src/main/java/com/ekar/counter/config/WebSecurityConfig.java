package com.ekar.counter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		/**
		 * Creating stateless so that once the auth header is used, it doesnt allow the requests in case the user uses wrong password
		 * after using correct one
		 */
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/actuator/**").permitAll()
		.antMatchers("/swagger-resources/**",
				"/swagger-ui.html",
				"/v2/api-docs",
				"/webjars/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("ekaruser").password(passwordEncoder().encode("ekarpassword"))
		.authorities("ROLE_USER");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}