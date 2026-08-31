package com.example.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final ChatClient chatClient;
    private final AgentTools agentTools;

    public AgentService(ChatClient chatClient, AgentTools agentTools) {
        this.chatClient = chatClient;
        this.agentTools = agentTools;
    }

    public String processTicket(String userMessage) {
        // Build the prompt with system instructions
        String systemPrompt = """
            You are an intelligent Ticket Routing Agent.
            Your job is to analyze the user's message and decide which tool to call.
            DO NOT create tickets yourself. ONLY call the provided tools.
            - If it's a bug -> call createBugTicket
            - If it's a new feature -> call createFeatureRequest
            - If it's a login/password issue -> call createAccountTicket
            If you don't know what to do, ask the user for clarification.
            """;

        // Use the tools! Spring AI will automatically inject the @Tool methods
        return chatClient.prompt()
                .system(systemPrompt)
                .user(userMessage)
                // This line tells Spring AI to use the tools from AgentTools bean
                .tools(agentTools)
                .call()
                .content();
    }
}
