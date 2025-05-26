package com.smart_physio_therapy.device_service.service.impl;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.repository.VitalSignsSensorRepository;
import com.smart_physio_therapy.device_service.service.ErrorHandlerService;
import com.smart_physio_therapy.device_service.service.SensorDataService;
import com.smart_physio_therapy.device_service.validator.ValidatorEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VitalSignsSensorService implements SensorDataService<VitalSignsSensor> {
    private final VitalSignsSensorRepository vitalSignsSensorRepository;

    private final ValidatorEngine validatorEngine;

    private final ErrorHandlerService errorHandlerService;

    @Override
    public void handleMessage(VitalSignsSensor vitalSignsData) {
        try {
            validatorEngine.validate(vitalSignsData);
            vitalSignsSensorRepository.save(vitalSignsData);
            log.debug("[VitalSignsSensorService]: Vital signs data has been sent and saved to MongoDB");
        } catch (InvalidDeviceDataException e) {
            log.warn("[VitalSignsSensorService]: Invalid data received: {}", e.getMessage());
            errorHandlerService.handleError("[VitalSignsSensorService]: Invalid data received",
                    e.getMessage());
        } catch (Exception e) {
            log.error("[VitalSignsSensorService]: Unexpected error occurred: {}", e.getMessage());
            errorHandlerService.handleError("[VitalSignsSensorService]: Unexpected error occurred:",
                    e.getMessage());
        }
    }
}
