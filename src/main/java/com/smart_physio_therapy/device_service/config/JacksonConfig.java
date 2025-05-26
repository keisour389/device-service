package com.smart_physio_therapy.device_service.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper configObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Support Java 8 datetime API, Ex: LocalDateTime
        mapper.registerModule(new JavaTimeModule());

        // Disable serialize LocalDateTime to timestamp
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        return mapper;
    }
}
