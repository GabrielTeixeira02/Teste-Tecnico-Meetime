package com.teste.tecnico.meetime.api.contact.dto;


import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Map;

@Data
public class ContactResponse {
    private String id;
    private Map<String, String> properties;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean archived;
}
