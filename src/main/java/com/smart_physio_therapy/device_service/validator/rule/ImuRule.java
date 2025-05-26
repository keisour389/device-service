package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import org.springframework.stereotype.Component;

@Component
public class ImuRule implements ValidatorRule<MusculoskeletalSystemSensor> {
    @Override
    public void validate(MusculoskeletalSystemSensor data) throws InvalidDeviceDataException {
        if (data.getImu() <= 0.0f || data.getImu() >= 500f) {
            throw new InvalidDeviceDataException("IMU out of range");
        }
    }
}
