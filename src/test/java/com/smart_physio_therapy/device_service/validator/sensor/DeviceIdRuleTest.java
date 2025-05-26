package com.smart_physio_therapy.device_service.validator.sensor;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.validator.rule.DeviceIdRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceIdRuleTest {
    private final DeviceIdRule deviceIdRule = new DeviceIdRule();

    @Test
    void shouldThrowExceptionWhenDeviceIdIsNull() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setDeviceId(null);

        InvalidDeviceDataException ex = assertThrows(
                InvalidDeviceDataException.class,
                () -> deviceIdRule.validate(data)
        );

        assertEquals("Device id is required", ex.getMessage());
    }

    @Test
    void shouldPassWhenDeviceIdIsValid() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setDeviceId("abc");

        assertDoesNotThrow(() -> deviceIdRule.validate(data));
    }
}
