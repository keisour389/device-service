package com.smart_physio_therapy.device_service.validator;

import com.smart_physio_therapy.device_service.model.VitalSignsSensor;
import com.smart_physio_therapy.device_service.validator.rule.ValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeviceDataValidator {
    // Inject any ValidationRule implementation
    private final List<ValidationRule> validationRules;

    public void validate(VitalSignsSensor vitalSignsSensor) {
        for (ValidationRule validationRule : validationRules) {
            validationRule.validate(vitalSignsSensor);
        }
    }
}
