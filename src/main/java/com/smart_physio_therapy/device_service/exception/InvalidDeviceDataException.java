package com.smart_physio_therapy.device_service.exception;

public class InvalidDeviceDataException extends RuntimeException {
    public InvalidDeviceDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDeviceDataException(String message) {
        super(message);
    }
}
