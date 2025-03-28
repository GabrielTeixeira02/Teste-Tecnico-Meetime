package com.teste.tecnico.meetime.api.webhook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.tecnico.meetime.api.contact.service.ContactService;
import com.teste.tecnico.meetime.api.webhook.dto.HubSpotWebhookEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebhookService {

    @Resource
    private ContactService contactService;

    @Value("${hubspot.webhook.secret}")
    private String webhookSecret;

    public void processContactCreation(HubSpotWebhookEvent event) {
        log.info("Processando criação de contato: ID {}", event.getObjectId());
    }

    public boolean isValidSignature(HubSpotWebhookEvent event, String signature, String version) {
        try {
            String payload = new ObjectMapper().writeValueAsString(event);
            String computedSignature = calculateSignature(payload, webhookSecret, version);
            return computedSignature.equals(signature);
        } catch (Exception e) {
            log.error("Erro ao validar assinatura", e);
            return false;
        }
    }

    private String calculateSignature(String payload, String secret, String version) {
        if ("v1".equals(version)) {
            return HmacUtils.hmacSha1Hex(secret, payload);
        } else if ("v2".equals(version)) {
            return HmacUtils.hmacSha256Hex(secret, payload);
        }
        throw new IllegalArgumentException("Versão de assinatura não suportada: " + version);
    }


}