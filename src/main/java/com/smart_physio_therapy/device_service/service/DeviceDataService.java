package com.smart_physio_therapy.device_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_physio_therapy.device_service.exception.DeviceDataParsingException;
import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.kafka.DeviceDataKafkaProducer;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.repository.DeviceDataRepository;
import com.smart_physio_therapy.device_service.validator.DeviceDataValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceDataService {
    private final ObjectMapper objectMapper;

    private final DeviceDataRepository deviceDataRepository;

    private final DeviceDataValidator deviceDataValidator;

    private final DeviceDataKafkaProducer deviceDataKafkaProducer;

    public void handleMessage(String message) {
        try {
            VitalSignsSensor data = objectMapper.readValue(message, VitalSignsSensor.class);
            deviceDataValidator.validate(data);
            deviceDataRepository.save(data);
            deviceDataKafkaProducer.send(data);
            log.debug("[DeviceDataService]: Device data has been sent and saved to MongoDB");
            // TODO: Send to Kafka later
        } catch (JsonProcessingException e) {
            throw new DeviceDataParsingException("Cannot parse the message from MQTT", e);
        } catch (InvalidDeviceDataException e) {
            log.warn("Invalid data received: {}", e.getMessage());
            // TODO: save to Kafka error topic / DB / audit
        }
    }
}
