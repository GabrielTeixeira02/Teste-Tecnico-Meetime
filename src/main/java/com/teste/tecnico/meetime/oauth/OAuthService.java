package com.teste.tecnico.meetime.oauth;

import com.teste.tecnico.meetime.client.HubSpotAuthClient;
import com.teste.tecnico.meetime.exception.HubSpotApiException;
import com.teste.tecnico.meetime.exception.OAuthException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class OAuthService {

    @Autowired
    private final HubSpotAuthClient hubSpotAuthClient;

    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.redirect-uri}")
    private String redirectUri;

    @Value("${hubspot.scopes}")
    private String scopes;

    @Value("${hubspot.auth-url}")
    private String authUrl;

    @Value("${hubspot.client-secret}")
    private String clientSecret;

    public String generateAuthorizationUrl() {
        try {
            return String.format("%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=code",
                    authUrl,
                    URLEncoder.encode(clientId, StandardCharsets.UTF_8),
                    URLEncoder.encode(redirectUri, StandardCharsets.UTF_8),
                    URLEncoder.encode(scopes, StandardCharsets.UTF_8));

        } catch (Exception e) {
            throw new OAuthException("Error generating authorization URL", e);
        }
    }

    public OAuthTokenResponse exchangeCodeForToken(String code) {
        try {
            return hubSpotAuthClient.getAccessToken(
                    "authorization_code",
                    code,
                    clientId,
                    clientSecret,
                    redirectUri
            );
        } catch (FeignException e) {
            throw new HubSpotApiException("Error exchanging code for token: " + e.contentUTF8(), e);
        }
    }

    public OAuthTokenResponse refreshToken(String refreshToken) {
        try {
            return hubSpotAuthClient.refreshAccessToken(
                    "refresh_token",
                    refreshToken,
                    clientId,
                    clientSecret
            );
        } catch (FeignException e) {
            throw new HubSpotApiException("Error refreshing token: " + e.contentUTF8(), e);
        }
    }
}
