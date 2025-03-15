package com.example.gatcha.controller;

import com.example.gatcha.service.InvocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invocation")
public class InvocationController {

    @Autowired
    private InvocationService invocationService;

    // Endpoint pour invoquer un monstre
    @PostMapping("/invoke")
    public ResponseEntity<String> invokeMonster(@RequestParam String playerId) {
        try {
            String monsterId = invocationService.processInvocation(playerId);
            return ResponseEntity.ok("Monster invoked with ID: " + monsterId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to invoke monster: " + e.getMessage());
        }
    }
}