package com.smart_physio_therapy.device_service.kafka;

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

    private final KafkaTemplate<String, VitalSignsSensor> kafkaTemplate;

    public void send(VitalSignsSensor data) {
        log.info("Sending data to Kafka topic: {}", data);
        kafkaTemplate.send(topic, data);
    }
}
