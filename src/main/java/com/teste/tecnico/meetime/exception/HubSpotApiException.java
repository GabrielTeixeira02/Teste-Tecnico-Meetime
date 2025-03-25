package com.teste.tecnico.meetime.exception;

import feign.FeignException;

public class HubSpotApiException extends RuntimeException {
    private final int statusCode;

    public HubSpotApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HubSpotApiException(String message, FeignException cause) {
        super(message, cause);
        this.statusCode = cause.status();
    }

    public int getStatusCode() {
        return statusCode;
    }
}