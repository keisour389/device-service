package com.smart_physio_therapy.device_service.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SensorType {
    VITAL_SIGNS("vitalSigns"),
    MUSCULOSKELETAL_SYSTEM("musculoskeletalSystem");

    private final String value;

    SensorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<SensorType> fromString(String value) {
        return Arrays.stream(SensorType.values())
                .filter(type -> type.value.equalsIgnoreCase(value))
                .findFirst();
    }
}
