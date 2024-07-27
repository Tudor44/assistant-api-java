package it.gdorsi.openaiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessagesListResponseDTO(
        String object,
        List<MessageResponseDTO> data,
        @JsonProperty("first_id")
        String firstId,
        @JsonProperty("last_id")
        String lastId,
        @JsonProperty("has_more")

        boolean hasMore) {}
