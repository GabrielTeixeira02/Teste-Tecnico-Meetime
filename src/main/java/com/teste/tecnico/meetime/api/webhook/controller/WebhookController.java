package com.teste.tecnico.meetime.api.webhook.controller;

import com.teste.tecnico.meetime.api.webhook.dto.HubSpotWebhookEvent;
import com.teste.tecnico.meetime.api.webhook.service.WebhookService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    private static final Logger logger = LogManager.getLogger();

    @Resource
    private WebhookService webhookService;

    @PostMapping("/contact-creation")
    public ResponseEntity<Void> handleContactCreation(
            @RequestBody List<HubSpotWebhookEvent> events,
            @RequestHeader("X-HubSpot-Signature") Optional<String> signature,
            @RequestHeader("X-HubSpot-Signature-Version") Optional<String> signatureVersion) {


        webhookService.processWebhookEvents(events, signature, signatureVersion);
        return ResponseEntity.ok().build();

    }
}