package it.gdorsi.openaiclient;

import it.gdorsi.openaiclient.dto.*;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {

        //LOAD YOUR API KEY
        Properties properties = new Properties();
        long DELAY = 3;// wait for a bit between status checks

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);

            AssistantAIClient client = new AssistantAIClient(properties);
            AssistantResponseDTO assistant = client.createAssistant(properties.getProperty("openai.assistant.instructions"));
            ThreadResponseDTO thread = client.createThread();
            client.sendMessage(thread.id(), "user", properties.getProperty("openai.user.prompt"));
            RunResponseDTO run = client.runMessage(thread.id(), assistant.id());

            waitUntilRunIsFinished(client, thread, run, DELAY);

            MessagesListResponseDTO allResponses = client.getMessages(thread.id());
            System.out.println("These are all the messages and you will be billed by OpenAI for every single one of them:");
            log(allResponses);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void waitUntilRunIsFinished(AssistantAIClient client, ThreadResponseDTO thread, RunResponseDTO run, long DELAY) throws InterruptedException {
        while (!isRunDone(client, thread.id(), run.id())) {
            superviseWorkInProgress(client, thread);
            sleep(DELAY * 1000);
        }
    }

    private static void superviseWorkInProgress(AssistantAIClient client, ThreadResponseDTO thread) {
        try {
            System.out.println("Checking messages to supervise assistant's work");
            MessagesListResponseDTO messages = client.getMessages(thread.id());
            log(messages);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void log(MessagesListResponseDTO messages) {
        messages.data().forEach(System.out::println);
    }

    private static boolean isRunDone(AssistantAIClient client, String threadId, String runId) {
        RunResponseDTO status;
        try {
            status = client.getRunStatus(threadId, runId);
            System.out.println("Status of your run is currently " + status);
            return isRunStateFinal(status);
        } catch (Exception e) {
            System.err.println("Failed to get run state, will retry..." + e);
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isRunStateFinal(RunResponseDTO runResponseDTO) {
        List<String> finalStates = List.of("cancelled", "failed", "completed", "expired"); //I consider these states as final
        String runStatus = Optional.of(runResponseDTO).map(RunResponseDTO::status).orElse("unknown").toLowerCase();
        return finalStates.contains(runStatus);
    }
}
