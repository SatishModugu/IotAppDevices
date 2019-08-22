package com.satish.iot.api.devices.service;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.satish.iot.api.devices.data.DeviceEntity;
import com.satish.iot.api.devices.data.DeviceRepository;
import com.satish.iot.api.devices.shared.DeviceDto;
@Service
public class DeviceServiceImpl implements DevicesService {
	DeviceRepository devicesRepository;
	
	@Autowired
	public DeviceServiceImpl(DeviceRepository devicesRepository)
	{
		this.devicesRepository = devicesRepository;
	}

	@Override
	public DeviceDto createDevice(DeviceDto deviceDetails) {
		deviceDetails.setDeviceID(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DeviceEntity deviceEntity = modelMapper.map(deviceDetails, DeviceEntity.class);
		devicesRepository.save(deviceEntity);
		DeviceDto returnValue = modelMapper.map(deviceEntity, DeviceDto.class);
		return returnValue;
	}

}
