package com.smart_physio_therapy.device_service.model;

import com.smart_physio_therapy.device_service.model.abstracts.SensorData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "musculoskeletal_system_sensor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MusculoskeletalSystemSensor extends SensorData implements Serializable {
    private float muscleForce;

    private float imu;
}
