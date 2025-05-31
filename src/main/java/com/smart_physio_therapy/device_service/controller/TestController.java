package com.smart_physio_therapy.device_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/devices/{id}")
    public ResponseEntity<String> getDeviceById(@PathVariable String id) {
        System.out.println(">>> Dịch vụ thiết bị giả lập được gọi cho ID: " + id);
        // Giả lập lỗi server nội bộ
        throw new IllegalArgumentException("Lỗi từ dịch vụ thiết bị giả lập");
    }
}
