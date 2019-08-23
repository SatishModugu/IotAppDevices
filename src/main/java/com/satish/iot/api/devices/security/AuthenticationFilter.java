package com.satish.iot.api.devices.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satish.iot.api.devices.ui.model.LoginRequestModel;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
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
	 }
	 
}
