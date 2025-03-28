package com.teste.tecnico.meetime.api.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Data
public class HubSpotWebhookEvent {
    @JsonProperty("eventId")
    private Long eventId;

    @JsonProperty("subscriptionId")
    private Long subscriptionId;

    @JsonProperty("portalId")
    private Long portalId;

    @JsonProperty("occurredAt")
    private Long occurredAt;

    @JsonProperty("subscriptionType")
    private String subscriptionType;

    @JsonProperty("objectId")
    private Long objectId;

    @JsonProperty("properties")
    private Map<String, String> properties;
}
