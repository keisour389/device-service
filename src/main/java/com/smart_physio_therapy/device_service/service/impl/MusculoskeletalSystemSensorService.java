package com.smart_physio_therapy.device_service.service.impl;

import com.smart_physio_therapy.device_service.kafka.DeviceDataKafkaProducer;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.repository.MusculoskeletalSystemSensorRepository;
import com.smart_physio_therapy.device_service.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusculoskeletalSystemSensorService implements SensorDataService<MusculoskeletalSystemSensor> {
    private final MusculoskeletalSystemSensorRepository musculoskeletalSystemSensorRepository;

    private final DeviceDataKafkaProducer deviceDataKafkaProducer;

    @Override
    public void handleMessage(MusculoskeletalSystemSensor musculoskeletalSystemData) {
        // TODO: Handle validation here
        // TODO: Handle @Transactional here
        deviceDataKafkaProducer.send(musculoskeletalSystemData);
        musculoskeletalSystemSensorRepository.save(musculoskeletalSystemData);
    }
}
