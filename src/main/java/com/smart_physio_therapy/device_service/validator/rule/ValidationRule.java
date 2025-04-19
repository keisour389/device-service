package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.DeviceData;

public interface ValidationRule {
    void validate(DeviceData data) throws InvalidDeviceDataException;
}
