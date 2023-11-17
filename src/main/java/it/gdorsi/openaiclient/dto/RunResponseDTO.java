package it.gdorsi.openaiclient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record RunResponseDTO(
        String id,
        String object,
        @JsonProperty("created_at")
        long createdAt,
        @JsonProperty("assistant_id")
        String assistantId,
        @JsonProperty("thread_id")
        String threadId,

        String status,
        @JsonProperty("started_at")
        Long startedAt, // Using Long to handle null values
        @JsonProperty("expires_at")
        Long expiresAt,
        @JsonProperty("cancelled_at")
        Long cancelledAt,
        @JsonProperty("failed_at")
        Long failedAt,
        @JsonProperty("completed_at")
        Long completedAt,
        @JsonProperty("last_error")
        String lastError,
        String model,
        String instructions,
        List<String> tools,
        @JsonProperty("file_ids")
        List<String> fileIds,
        Map<String, Object> metadata) {}
