package com.teste.tecnico.meetime.api.contact.service;

import com.teste.tecnico.meetime.client.HubSpotContactClient;
import com.teste.tecnico.meetime.api.contact.dto.ContactRequest;
import com.teste.tecnico.meetime.api.contact.dto.ContactResponse;
import com.teste.tecnico.meetime.exception.HubSpotApiException;
import com.teste.tecnico.meetime.exception.RateLimitExceededException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final HubSpotContactClient contactClient;

    public ContactService(HubSpotContactClient contactClient) {
        this.contactClient = contactClient;
    }

    @RateLimiter(name = "hubspotContactLimiter", fallbackMethod = "createContactFallback")
    @Retry(name = "hubspotRetry", fallbackMethod = "createContactFallback")
    public ContactResponse createContact(ContactRequest request) {
        return contactClient.createContact(request);
    }

    private ContactResponse createContactFallback(ContactRequest request, Exception ex) {
        if (ex instanceof RequestNotPermitted) {
            throw new RateLimitExceededException("Limite de taxa excedido. Máximo 110 requisições por 10 segundos.");
        }
        throw new HubSpotApiException("Falha ao criar contato: " + ex.getMessage(), 500);
    }
}
