package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import org.springframework.stereotype.Component;

@Component
public class PulseRule implements ValidatorRule<VitalSignsSensor> {
    @Override
    public void validate(VitalSignsSensor data) {
        if (data.getPulse() <= 0) {
            throw new InvalidDeviceDataException("Pulse must be greater than 0");
        }
    }
}
