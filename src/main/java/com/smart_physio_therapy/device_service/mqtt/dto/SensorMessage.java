package com.smart_physio_therapy.device_service.mqtt.dto;

import java.time.Instant;

public class SensorMessage<T> {
    private String sensorType;
    private String deviceId;
    private Instant timestamp;
    private T data;
}
