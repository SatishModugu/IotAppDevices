package com.satish.iot.api.devices.service;
import java.util.ArrayList;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.satish.iot.api.devices.data.DeviceEntity;
import com.satish.iot.api.devices.data.DeviceRepository;
import com.satish.iot.api.devices.shared.DeviceDto;
@Service
public class DeviceServiceImpl implements DevicesService {
	DeviceRepository devicesRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public DeviceServiceImpl(DeviceRepository devicesRepository,BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.devicesRepository = devicesRepository;
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
	}

	@Override
	public DeviceDto createDevice(DeviceDto deviceDetails) {
		deviceDetails.setDeviceID(UUID.randomUUID().toString());
		deviceDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(deviceDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DeviceEntity deviceEntity = modelMapper.map(deviceDetails, DeviceEntity.class);
		devicesRepository.save(deviceEntity);
		DeviceDto returnValue = modelMapper.map(deviceEntity, DeviceDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String devicename) throws UsernameNotFoundException {
		DeviceEntity deviceEntity = devicesRepository.findByDeviceID(devicename);
		if(deviceEntity==null) throw new UsernameNotFoundException(devicename);
		return new User(deviceEntity.getDeviceID(), deviceEntity.getEncryptedPassword(), true, true, true, true,new ArrayList<>());
	}

	@Override
	public DeviceDto getDeviceDetailsByID(String deviceID) {
		DeviceEntity deviceEntity = devicesRepository.findByDeviceID(deviceID);
		if(deviceEntity==null) throw new UsernameNotFoundException(deviceID);
		return new ModelMapper().map(deviceEntity, DeviceDto.class);
	}

}
