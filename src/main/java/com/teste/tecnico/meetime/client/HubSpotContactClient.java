package com.teste.tecnico.meetime.client;

import com.teste.tecnico.meetime.api.contact.dto.ContactRequest;
import com.teste.tecnico.meetime.api.contact.dto.ContactResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hubspot-contact-client", url = "${hubspot.api-url}")
public interface HubSpotContactClient {

    @PostMapping("/crm/v3/objects/contacts")
    ContactResponse createContact(@RequestBody ContactRequest contactRequest);
}
