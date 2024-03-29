package com.satish.iot.api.devices.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satish.iot.api.devices.service.DevicesService;
import com.satish.iot.api.devices.shared.DeviceDto;
import com.satish.iot.api.devices.ui.model.LoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private DevicesService deviceService;
	private Environment environment; 
	public AuthenticationFilter(DevicesService deviceService,Environment environment,AuthenticationManager authenticationManager)
	{
		this.deviceService = deviceService;
		this.environment= environment;
		super.setAuthenticationManager(authenticationManager);
	}
	 @Override
	    public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
	        try {
	  
	            LoginRequestModel creds = new ObjectMapper()
	                    .readValue(req.getInputStream(), LoginRequestModel.class);
	            
	            return getAuthenticationManager().authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            creds.getDeviceID(),
	                            creds.getPassword(),
	                            new ArrayList<>())
	            );
	            
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
		 String deviceName = ((User) auth.getPrincipal()).getUsername();
		 DeviceDto deviceDetails = deviceService.getDeviceDetailsByID(deviceName);
		 String token = Jwts.builder()
	                .setSubject(deviceDetails.getDeviceID())
	                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
	                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret") )
	                .compact();
		 res.addHeader("token", token);
		 res.addHeader("deviceID", deviceDetails.getDeviceID());
		 
	 }
	 
}
