package com.tech45degree.service;


import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RAGService {


    final YoutubeTranscripter youtubeTranscripter;
    final VectorStore vectorStore;
    final OpenAiChatModel chatModel;

    public RAGService(YoutubeTranscripter youtubeTranscripter, VectorStore vectorStore, OpenAiChatModel chatModel) {
        this.vectorStore = vectorStore;
        this.chatModel = chatModel;
        this.youtubeTranscripter = youtubeTranscripter;
    }

    public String uploadVideo(String videoId) {

        // Step 1: Transcribe YouTube Video
        String transcript = youtubeTranscripter.getTranscript(videoId);


        List<Document> documents = List.of(new Document(transcript));

        // Step 2: Add documents to the vector store for embedding
        vectorStore.add(documents);

        // Step 5: Return a success message after successfully uploading the Video
        return "Video uploaded successfully";
    }


    public String queryRAG(String userQuery) {

        // 1. Search for relevant documents from vector store
        SearchRequest searchRequest = SearchRequest.builder()
                .query(userQuery)
                .topK(2)
                .build();

        List<Document> retrievedDocs = vectorStore.similaritySearch(searchRequest);

        if (retrievedDocs == null || retrievedDocs.isEmpty()) {
            return "No relevant documents found.";
        }

        // 2. Build context string
        String context = retrievedDocs.stream()
                .map(Document::getText)
                .reduce("", (acc, text) -> acc + "\n" + text);

        // 3. Build full prompt (context + user query)

        // Define a template with a placeholder
        String templateText = """
                    Use the following context to answer the question.
    
                    Context:
                    '{context}'
    
                    Question: '{userQuery}'
                    """;

        // Create a PromptTemplate
        PromptTemplate promptTemplate = new PromptTemplate(templateText);

        // Render the prompt with a specific book title
        Prompt prompt = promptTemplate.create(Map.of("context", context, "userQuery", userQuery));


        // 4. Send prompt to LLM
        ChatResponse chatResponse =  chatModel.call(prompt);

        // 5. Extract answer
        return  chatResponse.getResult().getOutput().getText();
    }


}
