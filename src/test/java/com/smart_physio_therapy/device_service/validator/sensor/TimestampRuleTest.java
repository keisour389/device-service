package com.smart_physio_therapy.device_service.validator.sensor;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.validator.rule.TimestampRule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampRuleTest {
    private final TimestampRule timestampRule = new TimestampRule();

    @Test
    void shouldThrowExceptionTimestampGreaterThanNow() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setTimestamp(LocalDateTime.MAX);

        InvalidDeviceDataException ex = assertThrows(
                InvalidDeviceDataException.class,
                () -> timestampRule.validate(data)
        );

        assertEquals("Timestamp is in the future", ex.getMessage());
    }

    @Test
    void shouldPassWhenDeviceIdIsValid() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setTimestamp(LocalDateTime.now());

        assertDoesNotThrow(() -> timestampRule.validate(data));
    }
}
