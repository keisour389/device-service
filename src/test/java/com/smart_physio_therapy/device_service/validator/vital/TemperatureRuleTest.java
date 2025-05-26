package com.smart_physio_therapy.device_service.validator.vital;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.validator.rule.TemperatureRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureRuleTest {
    private final TemperatureRule temperatureRule = new TemperatureRule();

    @Test
    void shouldThrowExceptionWhenTemperatureIsInvalid() {
        VitalSignsSensor data = new VitalSignsSensor();
        data.setTemperature(29.9);

        InvalidDeviceDataException ex = assertThrows(
                InvalidDeviceDataException.class,
                () -> temperatureRule.validate(data)
        );

        assertEquals("Temperature must be between 30.0 and 42.0", ex.getMessage());
    }

    @Test
    void shouldPassWhenTemperatureIsValid() {
        VitalSignsSensor data = new VitalSignsSensor();
        data.setTemperature(41.9);

        assertDoesNotThrow(() -> temperatureRule.validate(data));
    }
}
