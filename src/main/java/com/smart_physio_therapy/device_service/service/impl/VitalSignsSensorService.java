package com.smart_physio_therapy.device_service.service.impl;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.kafka.DeviceDataKafkaProducer;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.repository.VitalSignsSensorRepository;
import com.smart_physio_therapy.device_service.service.SensorDataService;
import com.smart_physio_therapy.device_service.validator.VitalSignsSensorValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VitalSignsSensorService implements SensorDataService<VitalSignsSensor> {
    private final VitalSignsSensorRepository vitalSignsSensorRepository;

    private final VitalSignsSensorValidator vitalSignsSensorValidator;

    private final DeviceDataKafkaProducer deviceDataKafkaProducer;

    @Override
    public void handleMessage(VitalSignsSensor vitalSignsData) {
        try {
            vitalSignsSensorValidator.validate(vitalSignsData);
            // TODO: Handle @Transactional here
            deviceDataKafkaProducer.send(vitalSignsData);
            vitalSignsSensorRepository.save(vitalSignsData);
            log.debug("[DeviceDataService]: Vital signs data has been sent and saved to MongoDB");
            // TODO: Send to Kafka later
        } catch (InvalidDeviceDataException e) {
            log.warn("Invalid vital signs data received: {}", e.getMessage());
            // TODO: save to Kafka error topic / DB / audit
        }
    }
}
