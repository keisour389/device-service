package com.smart_physio_therapy.device_service.validator.musculoskeletal;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.validator.rule.ImuRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImuRuleTest {
    // Test real validate
    private final ImuRule imuRule = new ImuRule();

    @Test
    void shouldThrowExceptionWhenImuIsInvalid() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setImu(500.1f);

        InvalidDeviceDataException ex = assertThrows(
                InvalidDeviceDataException.class,
                () -> imuRule.validate(data)
        );

        assertEquals("IMU out of range", ex.getMessage());
    }

    @Test
    void shouldPassWhenImuIsValid() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setImu(0.1f);

        assertDoesNotThrow(() -> imuRule.validate(data));
    }
}
