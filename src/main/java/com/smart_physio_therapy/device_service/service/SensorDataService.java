package com.smart_physio_therapy.device_service.service;

import com.smart_physio_therapy.device_service.model.abstracts.SensorData;

public interface SensorDataService<T extends SensorData> {
    void handleMessage(T sensorData);
}
