package com.smart_physio_therapy.device_service.kafka;

import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeviceDataKafkaProducer {
    @Value("${kafka-config.topic.patient-health-data}")
    private String topic;

    private final KafkaTemplate<String, VitalSignsSensor> vitalSignsSensorKafkaTemplate;

    private final KafkaTemplate<String, MusculoskeletalSystemSensor> musculoskeletalSystemSensorKafkaTemplate;

    public void send(VitalSignsSensor data) {
        log.info("Sending vital signs data to Kafka topic: {}", data);
        vitalSignsSensorKafkaTemplate.send(topic, data);
    }

    public void send(MusculoskeletalSystemSensor data) {
        log.info("Sending musculoskeletal data to Kafka topic: {}", data);
        musculoskeletalSystemSensorKafkaTemplate.send(topic, data);
    }
}
