package com.prakhar.chatbot;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {


    @GetMapping("/")
    public String home() {
        return "Chatbot is running!";
    }

    private static final Map<String, String> chatbotResponses = new HashMap<>();

    static {
        chatbotResponses.put("hi", "Hello there!");
        chatbotResponses.put("hello", "Hi there!");
        chatbotResponses.put("hey", "Hey! How can I help you?");
        chatbotResponses.put("bye", "Goodbye");
        chatbotResponses.put("how are you", "I'm just a bot, but I'm doing great!");
        chatbotResponses.put("help", "Available commands: hi, hello, hey, bye, how are you, help");
        chatbotResponses.put("thanks", "You're welcome");
        chatbotResponses.put("thank you", "Glad I could help!");
    }

    @PostMapping("/webhook")
    public Map<String, Object> handleMessage(@RequestBody(required = false) Map<String, String> request) {

        String message = null;
        String reply;

        if (request != null) {
            message = request.get("message");
        }

        if (message == null || message.trim().isEmpty()) {
            reply = "Message cannot be empty!";
        } else {

            String normalizedMessage = message.trim().toLowerCase().replaceAll("\\s+", " ");

            reply = "Sorry, I didn’t understand that. Type 'help' to see available commands.";

            for (String key : chatbotResponses.keySet()) {
                if (normalizedMessage.contains(key)) {
                    reply = chatbotResponses.get(key);
                    break;
                }
            }
        }

        System.out.println("[" + LocalDateTime.now() + "] User: " + message + " | Bot: " + reply);

        Map<String, Object> response = new HashMap<>();
        response.put("reply", reply);
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "success");

        return response;
    }
}