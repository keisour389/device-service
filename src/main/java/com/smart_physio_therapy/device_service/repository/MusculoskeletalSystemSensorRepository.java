package com.smart_physio_therapy.device_service.repository;

import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusculoskeletalSystemSensorRepository extends MongoRepository<MusculoskeletalSystemSensor, String> {
}
