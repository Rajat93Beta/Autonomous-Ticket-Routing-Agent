package com.example.controller;

import com.example.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final AgentService agentService;

    public TicketController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody String userMessage) {
        String response = agentService.processTicket(userMessage);
        return ResponseEntity.ok(response);
    }
}
