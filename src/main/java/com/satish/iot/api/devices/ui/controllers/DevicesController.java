package com.satish.iot.api.devices.ui.controllers;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.satish.iot.api.devices.service.DevicesService;
import com.satish.iot.api.devices.shared.DeviceDto;
import com.satish.iot.api.devices.ui.model.CreateDeviceRequestModel;
import com.satish.iot.api.devices.ui.model.CreateDeviceResponseModel;

@RestController
@RequestMapping("/devices")
public class DevicesController {
	
	@Autowired
	private Environment env;
	@Autowired
	DevicesService devicesService;

	@GetMapping("/status/check")
	public String status() {
		return "working";
	}
	@PostMapping(
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CreateDeviceResponseModel> createDevice(@Valid @RequestBody CreateDeviceRequestModel deviceDetails)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DeviceDto deviceDto = modelMapper.map(deviceDetails, DeviceDto.class );
		DeviceDto createdDevice = devicesService.createDevice(deviceDto);
		CreateDeviceResponseModel returnValue = modelMapper.map(createdDevice,CreateDeviceResponseModel.class );
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
