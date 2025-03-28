package com.teste.tecnico.meetime.client;

import com.teste.tecnico.meetime.api.oauth.dto.OAuthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hubspot-auth-client", url = "${hubspot.token-url}")
public interface HubSpotAuthClient {

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    OAuthTokenResponse getAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("code") String code,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("redirect_uri") String redirectUri
    );

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    OAuthTokenResponse refreshAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("refresh_token") String refreshToken,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret
    );
}