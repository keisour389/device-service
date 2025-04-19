package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.DeviceData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimestampRule implements ValidationRule {
    @Override
    public void validate(DeviceData data) throws InvalidDeviceDataException {
        if (data.getTimestamp().isAfter(LocalDateTime.now())) {
            throw new InvalidDeviceDataException("Timestamp is in the future");
        }
    }
}
