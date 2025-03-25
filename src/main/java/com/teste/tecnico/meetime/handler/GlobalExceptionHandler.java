package com.teste.tecnico.meetime.handler;

import com.teste.tecnico.meetime.exception.HubSpotApiException;
import com.teste.tecnico.meetime.exception.OAuthException;
import com.teste.tecnico.meetime.handler.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HubSpotApiException.class)
    public ResponseEntity<ErrorResponse> handleHubSpotApiException(HubSpotApiException ex) {
        ErrorResponse error = new ErrorResponse(
                "HUBSPOT_API_ERROR",
                ex.getMessage(),
                ex.getStatusCode()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(OAuthException.class)
    public ResponseEntity<ErrorResponse> handleOAuthException(OAuthException ex) {
        ErrorResponse error = new ErrorResponse(
                "OAUTH_ERROR",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
