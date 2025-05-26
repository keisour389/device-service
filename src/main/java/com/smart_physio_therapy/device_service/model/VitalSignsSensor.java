package com.smart_physio_therapy.device_service.model;

import com.smart_physio_therapy.device_service.model.abstracts.SensorData;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "vital_signs_sensor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VitalSignsSensor extends SensorData implements Serializable {

    private int pulse;

    private double temperature;

    private float spo2;

    private float heartRate;
}
