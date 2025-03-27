package com.teste.tecnico.meetime.api.contact.controller;

import com.teste.tecnico.meetime.api.contact.dto.ContactRequest;
import com.teste.tecnico.meetime.api.contact.dto.ContactResponse;
import com.teste.tecnico.meetime.api.contact.service.ContactService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    @Resource
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponse> createContact(@RequestHeader(name = "Authorization") String token, @RequestBody ContactRequest contactRequest) {
        ContactResponse response = contactService.createContact(contactRequest, token);
        return ResponseEntity.ok(response);
    }
}