package com.teste.tecnico.meetime.api.webhook.controller;

import com.teste.tecnico.meetime.api.webhook.dto.HubSpotWebhookEvent;
import com.teste.tecnico.meetime.api.webhook.service.WebhookService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    private static final Logger logger = LogManager.getLogger();

    @Resource
    private WebhookService webhookService;

    @PostMapping("/contact-creation")
    public ResponseEntity<Void> handleContactCreation(
            @RequestBody HubSpotWebhookEvent event,
            @RequestHeader("X-HubSpot-Signature") Optional<String> signature,
            @RequestHeader("X-HubSpot-Signature-Version") Optional<String> signatureVersion) {

        if (!"contact.creation".equals(event.getSubscriptionType())) {
            logger.warn("Tipo de webhook não suportado: {}", event.getSubscriptionType());
            return ResponseEntity.badRequest().build();
        }

        if (signature.isPresent() && signatureVersion.isPresent()) {
            if (!webhookService.isValidSignature(event, signature.get(), signatureVersion.get())) {
                logger.error("Assinatura do webhook inválida");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        try {
            webhookService.processContactCreation(event);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Erro ao processar webhook de criação de contato", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
