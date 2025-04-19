package com.smart_physio_therapy.device_service.repository;

import com.smart_physio_therapy.device_service.model.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDataRepository extends MongoRepository<DeviceData, String> {
}