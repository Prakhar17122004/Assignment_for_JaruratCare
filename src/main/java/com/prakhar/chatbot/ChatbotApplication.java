package com.prakhar.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotApplication {

    public static void main(String[] args) {

        // 🔥 FORCE Railway PORT
        String port = System.getenv("PORT");
        if (port != null) {
            System.setProperty("server.port", port);
        }

        SpringApplication.run(ChatbotApplication.class, args);
    }
}