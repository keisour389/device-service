package com.smart_physio_therapy.device_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_physio_therapy.device_service.exception.DeviceDataParsingException;
import com.smart_physio_therapy.device_service.model.DeviceData;
import com.smart_physio_therapy.device_service.repository.DeviceDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataService {
    private static final Logger log = LoggerFactory.getLogger(DeviceDataService.class);
    private final ObjectMapper objectMapper;

    private final DeviceDataRepository deviceDataRepository;

    public DeviceDataService(ObjectMapper objectMapper,
                             DeviceDataRepository deviceDataRepository) {
        this.objectMapper = objectMapper;
        this. deviceDataRepository = deviceDataRepository;
    }

    public void handleMessage(String message) {
        try {
            DeviceData data = objectMapper.readValue(message, DeviceData.class);
            deviceDataRepository.save(data);
            log.debug("[DeviceDataService]: Device data has been save to MongoDB");
            // TODO: Send to Kafka later
        } catch (JsonProcessingException e) {
            throw new DeviceDataParsingException("Cannot parse the message from MQTT", e);
        }
    }
}
