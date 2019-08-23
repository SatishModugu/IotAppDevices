package com.satish.iot.api.devices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.satish.iot.api.devices.service.DevicesService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private Environment environment;
	private DevicesService deviceService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public WebSecurity(Environment environment,DevicesService deviceService,BCryptPasswordEncoder bCryptPasswordEncoder )
	{
		this.environment= environment; 
		this.deviceService = deviceService;
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/devices/**").permitAll();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("GatewayIP"))
		.and().addFilter(getAuthenticationFilter());
	}
	private AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(deviceService, environment, authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}
	  @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(deviceService).passwordEncoder(bCryptPasswordEncoder);
	    }

}
