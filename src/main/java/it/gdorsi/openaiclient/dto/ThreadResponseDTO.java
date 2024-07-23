package it.gdorsi.openaiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadResponseDTO(
        String id,
        String object,
        @JsonProperty("created_at")
        long createdAt,
        Map<String, Object> metadata) {}