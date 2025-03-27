package com.teste.tecnico.meetime.api.oauth.controller;

import com.teste.tecnico.meetime.api.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @GetMapping("/auth")
    public ResponseEntity<String> getAuthorizationUrl() {
        String url = oAuthService.generateAuthorizationUrl();
        return ResponseEntity.ok(url);
    }

    @GetMapping("/token")
    public ResponseEntity<?> exchangeCodeForToken(@RequestParam("code") String code) {
        var response = oAuthService.exchangeCodeForToken(code);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        var response = oAuthService.refreshToken(refreshToken);

        return ResponseEntity.ok(response);
    }
}

