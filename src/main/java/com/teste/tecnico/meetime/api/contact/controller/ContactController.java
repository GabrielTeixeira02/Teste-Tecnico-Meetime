package com.teste.tecnico.meetime.api.contact.controller;

import com.teste.tecnico.meetime.api.contact.dto.ContactRequest;
import com.teste.tecnico.meetime.api.contact.dto.ContactResponse;
import com.teste.tecnico.meetime.api.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponse> createContact(@RequestBody ContactRequest contactRequest) {
        ContactResponse response = contactService.createContact(contactRequest);
        return ResponseEntity.ok(response);
    }
}