package com.smart_physio_therapy.device_service.validator.rule;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.abstracts.SensorData;

public interface ValidatorRule<T extends SensorData>  {
    void validate(T data) throws InvalidDeviceDataException;
}
