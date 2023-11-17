package it.gdorsi.openaiclient;

import it.gdorsi.openaiclient.dto.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        //LOAD YOUR API KEY
        Properties properties = new Properties();

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);

            AssistantAIClient client = new AssistantAIClient(properties.getProperty("openai.api.key"));
            AssistantResponseDTO assistantResponse = client.createAssistant("You are an expert in geography, be helpful and concise.");
            ThreadResponseDTO threadResponse = client.createThread();
            MessageResponseDTO messageResponse = client.sendMessage(threadResponse.id(), "user", "What is the capital of Italy?");
            client.runMessage(threadResponse.id(), assistantResponse.id());
            //Retrieve and handle responses
            MessagesListResponseDTO response = client.getMessages(threadResponse.id());

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
