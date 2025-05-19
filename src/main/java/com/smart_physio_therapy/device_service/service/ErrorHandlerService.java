package com.smart_physio_therapy.device_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorHandlerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka-config.topic.dead-letter}")
    private String deadLetterTopic;

    public void handleError(String originalMessage, String errorReason) {
        try {
            ObjectNode dlqMessage = new ObjectMapper().createObjectNode();
            dlqMessage.put("originalPayload", originalMessage);
            dlqMessage.put("errorReason", errorReason);
            dlqMessage.put("timestamp", LocalDateTime.now().toString());

            // Send to DLQ
            kafkaTemplate.send(deadLetterTopic, dlqMessage.toString());
            log.warn("Sent message to Dead Letter Queue due to: {}", errorReason);
        } catch (Exception e) {
            log.error("Failed to send message to Dead Letter Queue: {}", errorReason);
        }
    }
}
