package com.satish.iot.api.devices.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="devices")
public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = 7979924361387771116L;
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable=false, length=50)
	private String deviceName;
	@Column(nullable=false, length=50)
	private String deviceType;
	private String deviceLocation;
	@Column(nullable=false, length=50, unique=true)
	private String deviceID;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
