package com.teste.tecnico.meetime.api.contact.service;

import com.teste.tecnico.meetime.api.contact.dto.ContactRequest;
import com.teste.tecnico.meetime.api.contact.dto.ContactResponse;
import com.teste.tecnico.meetime.client.HubSpotContactClient;
import com.teste.tecnico.meetime.exception.HubSpotApiException;
import com.teste.tecnico.meetime.exception.RateLimitExceededException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private HubSpotContactClient contactClient;

    @RateLimiter(name = "hubspotContactLimiter", fallbackMethod = "createContactFallback")
    public ContactResponse createContact(ContactRequest request, String token) {
        return contactClient.createContact(request, token);
    }

    private ContactResponse createContactFallback(Exception ex) throws Exception {
        if (ex instanceof RequestNotPermitted) {
            throw new RateLimitExceededException("Limite de taxa excedido. Máximo 110 requisições por 10 segundos.");
        }
        throw new HubSpotApiException("Falha ao criar contato: " + ex.getMessage(), 500);
    }
}
