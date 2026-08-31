package com.example.service;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import com.example.model.Ticket;
import com.example.repository.TicketRepository;

@Service
public class AgentTools {

    private final TicketRepository ticketRepository;

    public AgentTools(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Tool 1: Create a Bug Ticket
    @Tool(description = "Create a bug ticket in the system. Use this when the user reports a bug.")
    public String createBugTicket(@ToolParam("title of the bug") String title,
                                  @ToolParam("detailed description of the bug") String description) {
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setCategory("BUG");
        ticket.setAssignee("dev-team");
        ticketRepository.save(ticket);
        return "Ticket created successfully: #" + ticket.getId() + " (BUG)";
    }

    // Tool 2: Create a Feature Request
    @Tool(description = "Create a feature request ticket. Use this when the user asks for a new feature.")
    public String createFeatureRequest(@ToolParam("title of the feature") String title,
                                       @ToolParam("description of the feature") String description) {
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setCategory("FEATURE");
        ticket.setAssignee("product-team");
        ticketRepository.save(ticket);
        return "Feature request created successfully: #" + ticket.getId();
    }

    // Tool 3: Create an Account Issue Ticket
    @Tool(description = "Create an account issue ticket. Use this for login, password, or billing issues.")
    public String createAccountTicket(@ToolParam("issue summary") String summary,
                                      @ToolParam("user details") String user) {
        Ticket ticket = new Ticket();
        ticket.setTitle("Account Issue: " + summary);
        ticket.setDescription(user);
        ticket.setCategory("ACCOUNT");
        ticket.setAssignee("support-team");
        ticketRepository.save(ticket);
        return "Account issue created successfully: #" + ticket.getId();
    }

    // Helper to get tools for Spring AI
    public ToolCallbackProvider getToolCallbacks() {
        // Spring AI auto-discovers @Tool methods if they are beans
        return null; // Configuration handles this
    }
}
