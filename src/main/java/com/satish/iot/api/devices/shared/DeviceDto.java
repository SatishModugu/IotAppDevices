package com.satish.iot.api.devices.shared;

import java.io.Serializable;

public class DeviceDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4458395456086404171L;
	private String deviceName;
	private String deviceType;
	private String deviceLocation;
	private String deviceID;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceLocation() {
		return deviceLocation;
	}

	public void setDeviceLocation(String deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
}
