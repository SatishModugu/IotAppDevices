package com.satish.iot.api.devices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
	private BCryptPasswordEncoder bCryptPassswordEncoder;
	
	@Autowired
	public WebSecurity(Environment environment,DevicesService deviceService,BCryptPasswordEncoder bCryptPassswordEncode )
	{
		this.environment= environment; 
		this.deviceService = deviceService;
		this.bCryptPassswordEncoder= bCryptPassswordEncoder;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/devices/**").permitAll();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("GatewayIP"))
		.and().addFilter(new AuthenticationFilter());
	}
}
