package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import org.springframework.stereotype.Component;

@Component
public class TemperatureRule implements ValidatorRule<VitalSignsSensor> {
    @Override
    public void validate(VitalSignsSensor data) throws InvalidDeviceDataException {
        if (data.getTemperature() <= 30.0 || data.getTemperature() >= 42.0) {
            throw new InvalidDeviceDataException("Temperature must be between 30.0 and 42.0");
        }
    }
}
