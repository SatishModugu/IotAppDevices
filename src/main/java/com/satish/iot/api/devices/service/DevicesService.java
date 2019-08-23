package com.satish.iot.api.devices.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.satish.iot.api.devices.shared.DeviceDto;

public interface DevicesService extends UserDetailsService {
	DeviceDto createDevice(DeviceDto deviceDetails);
	DeviceDto getDeviceDetailsByID(String deviceID);
}
