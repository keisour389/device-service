package com.smart_physio_therapy.device_service.mqtt.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_physio_therapy.device_service.enums.SensorType;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.model.abstracts.SensorData;
import com.smart_physio_therapy.device_service.repository.VitalSignsSensorRepository;
import com.smart_physio_therapy.device_service.service.ErrorHandlerService;
import com.smart_physio_therapy.device_service.service.impl.MusculoskeletalSystemSensorService;
import com.smart_physio_therapy.device_service.service.impl.VitalSignsSensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SensorRouter {
    private final ObjectMapper objectMapper;

    private final MusculoskeletalSystemSensorService musculoskeletalSystemSensorService;

    private final VitalSignsSensorService vitalSignsSensorService;

    private final ErrorHandlerService errorHandlerService;

    public void routeAndHandle(String rawJson) {
        try {
            JsonNode root = objectMapper.readTree(rawJson);

            String sensorTypeStr = root.path("sensorType").asText().toLowerCase(Locale.ROOT);
            String deviceId = root.path("deviceId").asText();
            LocalDateTime timestamp = LocalDateTime.parse(root.path("timestamp").asText());

            JsonNode dataNode = root.path("data");

            Optional<SensorType> typeOpt = SensorType.fromString(sensorTypeStr);
            if (typeOpt.isEmpty()) {
                log.warn("Unknown sensor type: '{}'. Payload: {}", sensorTypeStr, rawJson);
                errorHandlerService.handleError("Unknown sensor type: " + sensorTypeStr, rawJson);
                return;
            }

            SensorType sensorType = typeOpt.get();
            switch (sensorType) {
                case VITAL_SIGNS:
                    routeToVitalSigns(dataNode, deviceId, timestamp);
                    break;

                case MUSCULOSKELETAL_SYSTEM:
                    routeToMusculoskeletalSystem(dataNode, deviceId, timestamp);
                    break;
            }
        } catch (JsonProcessingException e) {
            log.error("Failed to parse JSON payload. Payload: {} | Error: {}", rawJson, e.getMessage());
            errorHandlerService.handleError("Failed to parse JSON payload.", rawJson);
        } catch (Exception e) {
            log.error("Unexpected error while routing payload. Payload: {} | Error: {}", rawJson, e.getMessage(), e);
            errorHandlerService.handleError("Unexpected error while routing payload.", rawJson);
        }

    }

    public void routeToVitalSigns(JsonNode sensorData, String deviceId, LocalDateTime timestamp)
            throws JsonProcessingException {
        VitalSignsSensor vitalSignsData = objectMapper.treeToValue(sensorData, VitalSignsSensor.class);
        setDeviceInfo(vitalSignsData, deviceId, timestamp);
        // Message will be saved in DB
        vitalSignsSensorService.handleMessage(vitalSignsData);
    }

    public void routeToMusculoskeletalSystem(JsonNode sensorData, String deviceId, LocalDateTime timestamp)
            throws JsonProcessingException {
        MusculoskeletalSystemSensor musculoskeletalSystemSensor = objectMapper
                .treeToValue(sensorData, MusculoskeletalSystemSensor.class);
        setDeviceInfo(musculoskeletalSystemSensor, deviceId, timestamp);
        // Message will be saved in DB
        musculoskeletalSystemSensorService.handleMessage(musculoskeletalSystemSensor);
    }


    private void setDeviceInfo(SensorData sensorData, String deviceId, LocalDateTime timestamp) {
        sensorData.setDeviceId(deviceId);
        sensorData.setTimestamp(timestamp);
    }
}
