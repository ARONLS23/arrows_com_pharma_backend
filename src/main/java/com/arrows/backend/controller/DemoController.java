package com.arrows.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {
    @GetMapping("/ping")
    ResponseEntity<Map<String, Object>> ping() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("app", "pharma-btp");
        response.put("message", "Farmacia App corriendo en SAP BTP!");
        response.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("app", "pharma-btp");
        response.put("version", "1.0.0");
        response.put("endpoints", List.of(
                "GET  /api/ping",
                "GET  /api/info",
                "POST /api/echo",
                "GET  /actuator/health"
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo(@RequestBody Map<String, Object> body) {
        Map<String, Object> response = new HashMap<>();
        response.put("received", body);
        response.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/token")
    public ResponseEntity<Map<String, Object>> getToken(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("usage", "Copia el token y úsalo en Postman: Authorization: Bearer <token>");
        return ResponseEntity.ok(response);
    }
}
