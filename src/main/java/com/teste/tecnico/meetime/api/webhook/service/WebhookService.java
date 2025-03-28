package com.teste.tecnico.meetime.api.webhook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.tecnico.meetime.api.contact.service.ContactService;
import com.teste.tecnico.meetime.api.webhook.dto.HubSpotWebhookEvent;
import com.teste.tecnico.meetime.exception.InvalidWebhookException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WebhookService {

    @Value("${hubspot.client-secret}")
    private String webhookSecret;

    public void processWebhookEvents(List<HubSpotWebhookEvent> events,
                                     Optional<String> signature,
                                     Optional<String> signatureVersion) {
        events.forEach(event -> {
            validateEvent(event);
            verifySignatureIfPresent(event, signature, signatureVersion);
            processSingleEvent(event);
        });
    }

    private void validateEvent(HubSpotWebhookEvent event) {
        if (!"contact.creation".equals(event.getSubscriptionType())) {
            throw new InvalidWebhookException("Tipo de webhook não suportado: " + event.getSubscriptionType());
        }
    }

    private void verifySignatureIfPresent(HubSpotWebhookEvent event,
                                          Optional<String> signature,
                                          Optional<String> signatureVersion) {
        if (signature.isPresent() && signatureVersion.isPresent()) {
            if (!isValidSignature(event, signature.get(), signatureVersion.get())) {
                throw new SecurityException("Assinatura do webhook inválida");
            }
        }
    }

    private void processSingleEvent(HubSpotWebhookEvent event) {
        log.info("Processando criação de contato: ID {}", event.getObjectId());
    }

    public boolean isValidSignature(HubSpotWebhookEvent event, String signature, String version) {
        try {
            String payload = new ObjectMapper().writeValueAsString(event);
            String computedSignature = calculateSignature(payload, version);
            return computedSignature.equals(signature);
        } catch (Exception e) {
            log.error("Erro ao validar assinatura", e);
            return false;
        }
    }

    private String calculateSignature(String payload, String version) {
        return switch (version) {
            case "v1" -> HmacUtils.hmacSha1Hex(webhookSecret, payload);
            case "v2" -> HmacUtils.hmacSha256Hex(webhookSecret, payload);
            default -> throw new IllegalArgumentException("Versão de assinatura não suportada: " + version);
        };
    }
}