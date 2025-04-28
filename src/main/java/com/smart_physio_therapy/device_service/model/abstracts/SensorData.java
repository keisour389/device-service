package com.smart_physio_therapy.device_service.model.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class SensorData {
    @Id
    private String id;

    private String userId;

    private LocalDateTime timestamp;
}
