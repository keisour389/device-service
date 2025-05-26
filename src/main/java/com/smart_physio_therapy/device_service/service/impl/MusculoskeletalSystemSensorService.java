package com.smart_physio_therapy.device_service.service.impl;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.repository.MusculoskeletalSystemSensorRepository;
import com.smart_physio_therapy.device_service.service.ErrorHandlerService;
import com.smart_physio_therapy.device_service.service.SensorDataService;
import com.smart_physio_therapy.device_service.validator.ValidatorEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusculoskeletalSystemSensorService implements SensorDataService<MusculoskeletalSystemSensor> {

    private final MusculoskeletalSystemSensorRepository musculoskeletalSystemSensorRepository;

    private final ErrorHandlerService errorHandlerService;

    private final ValidatorEngine validatorEngine;

    @Override
    public void handleMessage(MusculoskeletalSystemSensor musculoskeletalSystemData) {
        try {
            validatorEngine.validate(musculoskeletalSystemData);
            musculoskeletalSystemSensorRepository.save(musculoskeletalSystemData);
            log.debug("[MusculoskeletalSystemSensorService]: Musculoskeletal system data has been sent" +
                    " and saved to MongoDB");
        } catch (InvalidDeviceDataException e) {
            log.warn("[MusculoskeletalSystemSensorService]: Invalid data received: {}", e.getMessage());
            errorHandlerService.handleError("[MusculoskeletalSystemSensorService]: Invalid data received",
                    e.getMessage());
        } catch (Exception e) {
            log.error("[MusculoskeletalSystemSensorService]: Unexpected error occurred: {}", e.getMessage());
            errorHandlerService.handleError("[MusculoskeletalSystemSensorService]: Unexpected " +
                            "error occurred:", e.getMessage());
        }
    }
}
