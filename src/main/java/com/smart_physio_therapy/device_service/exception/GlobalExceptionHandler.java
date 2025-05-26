package com.smart_physio_therapy.device_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body("System error, cause: " + ex.getMessage());
    }

    @ExceptionHandler(DeviceDataParsingException .class)
    public ResponseEntity<String> handleParse(DeviceDataParsingException ex) {
        return ResponseEntity.badRequest().body("Data parse has been failed, cause: " + ex.getMessage());
    }
}
