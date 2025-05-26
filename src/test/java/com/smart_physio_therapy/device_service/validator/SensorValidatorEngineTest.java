package com.smart_physio_therapy.device_service.validator;

import com.smart_physio_therapy.device_service.exception.InvalidDeviceDataException;
import com.smart_physio_therapy.device_service.model.MusculoskeletalSystemSensor;
import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.model.abstracts.SensorData;
import com.smart_physio_therapy.device_service.validator.rule.DeviceIdRule;
import com.smart_physio_therapy.device_service.validator.rule.ImuRule;
import com.smart_physio_therapy.device_service.validator.rule.PulseRule;
import com.smart_physio_therapy.device_service.validator.rule.ValidatorRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class SensorValidatorEngineTest {

    ValidatorEngine validatorEngine;

    ValidatorEngine emptyValidatorEngine = new ValidatorEngine(List.of());

    @Mock
    DeviceIdRule deviceIdRule;

    @Mock
    PulseRule pulseRule;

    @Mock
    ImuRule imuRule;

    @BeforeEach
    void setUp() {
        List<ValidatorRule<?>> allRules = List.of(deviceIdRule, pulseRule, imuRule);
        validatorEngine = new ValidatorEngine(allRules);
        emptyValidatorEngine = new ValidatorEngine(List.of());
    }

    @Test
    void shouldInvokeCommonAndSpecificRulesForVitalSignsSensor() {
        VitalSignsSensor vitalData = new VitalSignsSensor();
        vitalData.setDeviceId("ABC123");
        vitalData.setPulse(80);

        Mockito.doNothing().when(deviceIdRule).validate(Mockito.any());
        Mockito.doNothing().when(pulseRule).validate(Mockito.any());

        validatorEngine.validate(vitalData);

        Mockito.verify(deviceIdRule, times(1)).validate(vitalData);
        Mockito.verify(pulseRule, times(1)).validate(vitalData);
        verifyNoInteractions(imuRule);
    }

    @Test
    void shouldInvokeCommonAndSpecificRulesForMusculoskeletalSensor() {
        MusculoskeletalSystemSensor muscleData = new MusculoskeletalSystemSensor();
        muscleData.setDeviceId("XYZ456");
        muscleData.setImu(2.5f);
        muscleData.setMuscleForce(5.0f);

        Mockito.doNothing().when(deviceIdRule).validate(Mockito.any());
        Mockito.doNothing().when(imuRule).validate(Mockito.any());

        validatorEngine.validate(muscleData);

        Mockito.verify(deviceIdRule, times(1)).validate(muscleData);
        Mockito.verify(imuRule, times(1)).validate(muscleData);
        verifyNoInteractions(pulseRule);
    }

    @Test
    void shouldThrowExceptionIfRuleFails() {
        VitalSignsSensor vitalData = new VitalSignsSensor();
        vitalData.setDeviceId(null); // Missing ID

        Mockito.doThrow(new InvalidDeviceDataException("Device ID is required"))
                .when(deviceIdRule).validate(Mockito.any());

        InvalidDeviceDataException thrown = assertThrows(
                InvalidDeviceDataException.class,
                () -> validatorEngine.validate(vitalData)
        );

        assertEquals("Device ID is required", thrown.getMessage());
        Mockito.verify(deviceIdRule).validate(vitalData);
    }

    @Test
    void shouldSkipValidationIfNoRuleMatchesInVitalSignsSensor() {
        VitalSignsSensor data = new VitalSignsSensor();
        data.setDeviceId(null);
        data.setPulse(-1);

        assertDoesNotThrow(() -> validatorEngine.validate(data));
    }

    @Test
    void shouldSkipValidationIfNoRuleMatchesInMusculoskeletalSensor() {
        MusculoskeletalSystemSensor data = new MusculoskeletalSystemSensor();
        data.setDeviceId(null);
        data.setMuscleForce(-1);

        assertDoesNotThrow(() -> validatorEngine.validate(data));
    }
}
