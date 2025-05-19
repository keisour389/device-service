package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;

public interface MusculoskeletalRule {
    void validate(MusculoskeletalSystemSensor data) throws InvalidDeviceDataException;
}
