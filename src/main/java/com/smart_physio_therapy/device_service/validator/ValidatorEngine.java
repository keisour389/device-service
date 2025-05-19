package com.smart_physio_therapy.device_service.validator;

import com.smart_physio_therapy.device_service.model.abstracts.SensorData;
import com.smart_physio_therapy.device_service.validator.rule.ValidatorRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidatorEngine {
    private final List<ValidatorRule<?>> rules;

    public <T extends SensorData> void validate(T data) {
        for (ValidatorRule<?> rule : rules) {
            if (supports(rule, data)) {
                invoke(rule, data);
            }
        }
    }

    private <T extends SensorData> boolean supports(ValidatorRule<?> rule, T data) {
        // So sánh generic type
        Type[] genericInterfaces = rule.getClass().getGenericInterfaces();
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                Type actualType = ((ParameterizedType) type).getActualTypeArguments()[0];
                if (actualType instanceof Class && ((Class<?>) actualType).isAssignableFrom(data.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private <T extends SensorData> void invoke(ValidatorRule<?> rule, T data) {
        ((ValidatorRule<T>) rule).validate(data);
    }
}
