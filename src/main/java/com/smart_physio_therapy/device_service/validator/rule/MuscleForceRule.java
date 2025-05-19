package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import org.springframework.stereotype.Component;

@Component
public class MuscleForceRule implements ValidatorRule<MusculoskeletalSystemSensor> {
    @Override
    public void validate(MusculoskeletalSystemSensor data) throws InvalidDeviceDataException {
        if (data.getMuscleForce() <= 0.0f || data.getMuscleForce() >= 1500f) {
            throw new InvalidDeviceDataException("Muscle force out of range");
        }
    }
}
