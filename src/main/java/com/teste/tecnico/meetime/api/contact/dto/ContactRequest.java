package com.teste.tecnico.meetime.api.contact.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ContactRequest {
    @NotEmpty
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String company;
}
