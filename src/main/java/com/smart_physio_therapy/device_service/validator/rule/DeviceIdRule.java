package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.DeviceData;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeviceIdRule implements ValidationRule{
    @Override
    public void validate(DeviceData data) throws InvalidDeviceDataException {
        if (Objects.isNull(data.getDeviceId()) || data.getDeviceId().isEmpty()) {
            throw new InvalidDeviceDataException("Device id is required");
        }
    }
}
