package com.smart_physio_therapy.device_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_physio_therapy.device_service.exception.DeviceDataParsingException;
import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.DeviceData;
import com.smart_physio_therapy.device_service.repository.DeviceDataRepository;
import com.smart_physio_therapy.device_service.validator.DeviceDataValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceDataService {
    private final ObjectMapper objectMapper;

    private final DeviceDataRepository deviceDataRepository;

    private final DeviceDataValidator deviceDataValidator;

    public DeviceDataService(ObjectMapper objectMapper,
                             DeviceDataRepository deviceDataRepository,
                             DeviceDataValidator deviceDataValidator) {
        this.objectMapper = objectMapper;
        this.deviceDataRepository = deviceDataRepository;
        this.deviceDataValidator = deviceDataValidator;
    }

    public void handleMessage(String message) {
        try {
            DeviceData data = objectMapper.readValue(message, DeviceData.class);
            deviceDataValidator.validate(data);
            deviceDataRepository.save(data);
            log.debug("[DeviceDataService]: Device data has been save to MongoDB");
            // TODO: Send to Kafka later
        } catch (JsonProcessingException e) {
            throw new DeviceDataParsingException("Cannot parse the message from MQTT", e);
        } catch (InvalidDeviceDataException e) {
            log.warn("Invalid data received: {}", e.getMessage());
            // TODO: save to Kafka error topic / DB / audit
        }
    }
}
