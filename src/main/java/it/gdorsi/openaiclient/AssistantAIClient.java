package it.gdorsi.openaiclient;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.gdorsi.openaiclient.dto.*;

public class AssistantAIClient {
    private final String apiKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AssistantAIClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    private String post(String url, Object body) throws Exception {
        String jsonBody = objectMapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .header("OpenAI-Beta", "assistants=v1")  // Add this line
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public AssistantResponseDTO createAssistant(String initialPrompt) throws Exception {
        String url = "https://api.openai.com/v1/assistants";
        AssistantRequestDTO dto = new AssistantRequestDTO("gpt-4-1106-preview", initialPrompt);
        String response = post(url, dto);
        return objectMapper.readValue(response, AssistantResponseDTO.class);
    }

    public ThreadResponseDTO createThread() throws Exception {
        String url = "https://api.openai.com/v1/threads";

        String response = post(url, "");
        return objectMapper.readValue(response, ThreadResponseDTO.class);
    }

    public MessageResponseDTO sendMessage(String threadId, String role, String content) throws Exception {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        MessageDTO dto = new MessageDTO(role, content);

        String response = post(url, dto);
        return objectMapper.readValue(response, MessageResponseDTO.class);
    }

    public RunResponseDTO runMessage(String threadId, String assistantId) throws Exception {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/runs";
        RunRequestDTO dto = new RunRequestDTO(assistantId);

        String response = post(url, dto);
        return objectMapper.readValue(response, RunResponseDTO.class);
    }

    public MessagesListResponseDTO getMessages(String threadId) throws Exception {
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("OpenAI-Beta", "assistants=v1")
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), MessagesListResponseDTO.class);
        // Assuming the response is a JSON array of MessageResponseDTO
        //return objectMapper.readValue(response.body(), new TypeReference<List<MessageResponseDTO>>() {});
    }
}
