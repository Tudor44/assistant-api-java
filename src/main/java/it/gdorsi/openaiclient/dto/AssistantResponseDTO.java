package it.gdorsi.openaiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AssistantResponseDTO(
        String id,
        String object,
        @JsonProperty("created_at")
        long createdAt,
        String name,
        String description,
        String model,
        String instructions,
        List<String> tools,
        @JsonProperty("file_ids")
        List<String> fileIds,
        Map<String, Object> metadata) {}