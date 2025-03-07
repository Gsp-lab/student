package com.sms.student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sms.student.filter.StudentFilter;
import com.sms.student.service.StudentServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	private StudentFilter studentFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(studentServiceImpl);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable().logout().and().exceptionHandling().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(studentFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
