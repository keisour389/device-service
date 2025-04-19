package com.smart_physio_therapy.device_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "device_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceData {
    @Id
    private String id;

    private String deviceId;
    private LocalDateTime timestamp;
    private int pulse;
    private double temperature;

}
