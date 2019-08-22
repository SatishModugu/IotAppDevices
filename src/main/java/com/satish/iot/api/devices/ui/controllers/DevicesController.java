package com.satish.iot.api.devices.ui.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satish.iot.api.devices.ui.model.CreateDeviceRequestModel;

@RestController
@RequestMapping("/devices")
public class DevicesController {

	@GetMapping("/status/check")
	public String status() {
		return "working";
	}
	@PostMapping
	public String createUser(@Valid @RequestBody CreateDeviceRequestModel deviceDetails)
	{
		return "";
	}
}
