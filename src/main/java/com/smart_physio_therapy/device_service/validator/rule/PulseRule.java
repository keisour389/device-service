package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.DeviceData;
import org.springframework.stereotype.Component;

@Component
public class PulseRule implements ValidationRule {
    @Override
    public void validate(DeviceData data) {
        if (data.getPulse() <= 0) {
            throw new InvalidDeviceDataException("Pulse must be greater than 0");
        }
    }
}
