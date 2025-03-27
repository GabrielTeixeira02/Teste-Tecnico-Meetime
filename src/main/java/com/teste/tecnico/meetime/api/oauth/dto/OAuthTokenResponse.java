package com.teste.tecnico.meetime.api.oauth.dto;

import lombok.Data;

@Data
public class OAuthTokenResponse {
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private String token_type;
}
