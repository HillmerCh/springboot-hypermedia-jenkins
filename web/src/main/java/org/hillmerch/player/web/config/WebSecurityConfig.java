package org.hillmerch.player.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/api-docs","/api-docs/**", "/api-docs-ui.html", "/swagger-ui/**")//To
				.permitAll()
				.anyRequest().authenticated()
				.and()
				.oauth2Login();
	}
}
