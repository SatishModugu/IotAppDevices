package com.satish.iot.api.devices.data;

import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {
	DeviceEntity findByDeviceID(String deviceID);
}
