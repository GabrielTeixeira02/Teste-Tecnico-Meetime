package com.teste.tecnico.meetime.exception;

public class InvalidWebhookException extends RuntimeException {
  public InvalidWebhookException(String message) {
    super(message);
  }
}
