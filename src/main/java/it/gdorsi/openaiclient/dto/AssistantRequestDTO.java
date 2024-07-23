package it.gdorsi.openaiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AssistantRequestDTO(String model, String instructions) {}


