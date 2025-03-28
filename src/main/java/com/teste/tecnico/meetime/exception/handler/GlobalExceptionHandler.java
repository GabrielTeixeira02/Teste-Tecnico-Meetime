package com.teste.tecnico.meetime.exception.handler;

import com.teste.tecnico.meetime.exception.HubSpotApiException;
import com.teste.tecnico.meetime.exception.InvalidWebhookException;
import com.teste.tecnico.meetime.exception.OAuthException;
import com.teste.tecnico.meetime.exception.RateLimitExceededException;
import com.teste.tecnico.meetime.exception.handler.dto.ErrorResponse;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger();

    @ExceptionHandler(HubSpotApiException.class)
    public ResponseEntity<ErrorResponse> handleHubSpotApiException(HubSpotApiException ex) {
        ErrorResponse error = new ErrorResponse(
                "HUBSPOT_API_ERROR",
                ex.getMessage(),
                ex.getStatusCode()
        );

        logger.error(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(OAuthException.class)
    public ResponseEntity<ErrorResponse> handleOAuthException(OAuthException ex) {
        ErrorResponse error = new ErrorResponse(
                "OAUTH_ERROR",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        logger.error(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleRateLimitExceeded(RateLimitExceededException ex) {
        ErrorResponse error = new ErrorResponse(
                "RATE_LIMIT_EXCEEDED",
                ex.getMessage(),
                429
        );

        logger.error(ex.getMessage());

        return ResponseEntity.status(429).body(error);
    }

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<ErrorResponse> handleFeignForbiddenException(FeignException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.FORBIDDEN.toString(),
                ex.getMessage(),
                ex.status()
        );

        logger.error(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.status()));
    }

    @ExceptionHandler(InvalidWebhookException.class)
    public ResponseEntity<ErrorResponse> handleInvalidWebhookException(InvalidWebhookException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        logger.error(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponse> handleSecurityException(SecurityException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.FORBIDDEN.toString(),
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );

        logger.error(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.valueOf(HttpStatus.FORBIDDEN.value()));
    }

}
