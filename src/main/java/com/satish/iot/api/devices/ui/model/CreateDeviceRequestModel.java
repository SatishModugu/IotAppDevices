package com.satish.iot.api.devices.ui.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDeviceRequestModel {
	@NotNull(message="First Name cannot be null")
	private String deviceName;
	@NotNull(message="deviceID Name cannot be null")
	@Size(min=4,max=16,message="ID should be greater than 4 and less than 16")
	private String deviceType;
	private String deviceLocation;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceID() {
		return deviceType;
	}

	public void setDeviceID(String deviceID) {
		this.deviceType = deviceID;
	}

	public String getDeviceLocation() {
		return deviceLocation;
	}

	public void setDeviceLocation(String deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

}
