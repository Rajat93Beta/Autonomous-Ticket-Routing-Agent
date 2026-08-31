# Autonomous Ticket Routing Agent

An **AI Agent** built with **Spring Boot 3** and **Spring AI** that automatically routes support tickets to the correct team using **Function Calling**.

## 🚀 Features
- **Intelligent Routing**: Uses LLM to analyze user intent (Bug vs Feature vs Account).
- **Function Calling**: AI automatically executes Java methods (`@Tool`) to create tickets.
- **Team Assignment**: Automatically assigns tickets to `dev-team`, `product-team`, or `support-team`.
- **Spring AI Integration**: Leverages Spring AI's tool callback mechanism.

## 🛠️ Tech Stack
- **Backend**: Spring Boot 3, Java 17
- **AI**: Spring AI (OpenAI GPT-4o-mini)
- **Database**: PostgreSQL (via Spring Data JDBC)
- **Pattern**: Agentic Workflow / Function Calling

## 🧪 How it Works
1. User sends a message: "I can't login to my account, it says password is wrong."
2. Agent analyzes intent → Decides to call `createAccountTicket`.
3. Agent executes the Java method → Saves ticket to DB.
4. Agent returns: "Account issue created successfully: #123 assigned to support-team."

## 🚀 Run Locally
1. Create a PostgreSQL DB named `ticket_db`.
2. Set `OPENAI_API_KEY` in `application.yml`.
3. Run: `mvn spring-boot:run`.
4. Test:
   ```bash
   curl -X POST http://localhost:8080/api/ticket \
     -H "Content-Type: application/json" \
     -d '"The login button is broken, it does nothing!"'
   ```

## 🎯 Interview Talking Point
"This project demonstrates Agentic Workflows. Unlike a simple chatbot, this agent can act on the world by executing Java code (creating tickets) based on its understanding of user intent. It shows how to integrate LLM decision making with deterministic business logic."

---

## 🚀 How to Run & Test

1. **Setup PostgreSQL**:
   - Install PostgreSQL.
   - Create a database: `CREATE DATABASE ticket_db;`
2. **Run the App**:
   ```bash
   mvn spring-boot:run
   ```
3. **Test the Agent**:
   - **Bug**: `The search page is returning 404 errors.`
     - *Expected*: Agent calls `createBugTicket` → Ticket created for `dev-team`.
   - **Feature**: `I want a dark mode feature.`
     - *Expected*: Agent calls `createFeatureRequest` → Ticket created for `product-team`.
   - **Account**: `I forgot my password.`
     - *Expected*: Agent calls `createAccountTicket` → Ticket created for `support-team`.

---

## 💡
1. **It's "Agentic"**: You aren't just chatting; you are **acting**.
2. **It's Enterprise-Ready**: Real companies need agents that can create Jira tickets, send emails, or update databases.
3. **It Shows Deep Spring AI Knowledge**: Using `@Tool` and `ToolCallbackProvider` is an advanced Spring AI feature.
