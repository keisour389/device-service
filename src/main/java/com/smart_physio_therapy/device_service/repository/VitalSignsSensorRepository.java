package com.smart_physio_therapy.device_service.repository;

import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalSignsSensorRepository extends MongoRepository<VitalSignsSensor, String> {
}