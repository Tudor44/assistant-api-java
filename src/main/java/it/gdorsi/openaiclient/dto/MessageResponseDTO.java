package it.gdorsi.openaiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageResponseDTO(
        String id,
        String object,
        @JsonProperty("created_at")
        long createdAt,
        @JsonProperty("thread_id")
        String threadId,
        String role,
        List<Content> content,
        @JsonProperty("file_ids")
        List<String> fileIds,
        @JsonProperty("assistant_id")
        String assistantId,
        @JsonProperty("run_id")
        String runId,
        Map<String, Object> metadata) {

    public static record Content(
            String type,
            Text text) {

        public static record Text(
                String value,
                List<Object> annotations) {}
    }
}