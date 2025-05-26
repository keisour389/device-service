package com.smart_physio_therapy.device_service.validator.vital;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.validator.rule.PulseRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PulseRuleTest {
    private final PulseRule pulseRule = new PulseRule();

    @Test
    void shouldThrowExceptionWhenPulseIsInvalid() {
        VitalSignsSensor data = new VitalSignsSensor();
        data.setPulse(-1);

        InvalidDeviceDataException ex = assertThrows(
                InvalidDeviceDataException.class,
                () -> pulseRule.validate(data)
        );

        assertEquals("Pulse must be greater than 0", ex.getMessage());
    }

    @Test
    void shouldPassWhenPulseIsValid() {
        VitalSignsSensor data = new VitalSignsSensor();
        data.setPulse(70);

        assertDoesNotThrow(() -> pulseRule.validate(data));
    }
}
