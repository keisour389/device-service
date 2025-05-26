package com.smart_physio_therapy.device_service.validator.musculoskeletal;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.validator.rule.MuscleForceRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MuscleForceRuleTest {
    private final MuscleForceRule muscleForceRule = new MuscleForceRule();

    @Test
    void shouldThrowExceptionWhenMuscleForceIsInvalid() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setMuscleForce(1500.1f);

        InvalidDeviceDataException ex = assertThrows(
                InvalidDeviceDataException.class,
                () -> muscleForceRule.validate(data)
        );

        assertEquals("Muscle force out of range", ex.getMessage());
    }

    @Test
    void shouldPassWhenMuscleForceIsValid() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setMuscleForce(0.1f);

        assertDoesNotThrow(() -> muscleForceRule.validate(data));
    }

}
