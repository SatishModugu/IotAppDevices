package com.satish.iot.api.devices.service;

import java.util.UUID;

import com.satish.iot.api.devices.shared.DeviceDto;

public class DeviceServiceImpl implements DevicesService {

	@Override
	public DeviceDto createUser(DeviceDto deviceDetails) {
		deviceDetails.setDeviceID(UUID.randomUUID().toString());
		return null;
	}

}
