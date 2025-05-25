package com.tech45degree.service;


import com.tech45degree.util.PdfReader;
import com.tech45degree.util.SemanticChunker;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RAGService {


    final PdfReader pdfReader;
    final SemanticChunker semanticChunker;
    final VectorStore vectorStore;
    final OpenAiChatModel chatModel;

    public RAGService(PdfReader pdfReader, SemanticChunker semanticChunker, VectorStore vectorStore, OpenAiChatModel chatModel) {
        this.pdfReader = pdfReader;
        this.semanticChunker = semanticChunker;
        this.vectorStore = vectorStore;
        this.chatModel = chatModel;
    }

    public String uploadPDF(String uploadedFilePath) {

        // Step 1: Read the PDF file and convert it into a list of Document objects
        List<Document> documents = pdfReader.readPdf(uploadedFilePath);

        // Step 2: Chunk the documents into smaller paragraphs for embedding
        List<Document> chunks = semanticChunker.chunkParagraphs(documents);

        // Step 3: Filter out any chunks that are null or blank to ensure only valid data is processed
        List<Document> validChunks = chunks.stream()
                .filter(doc -> doc.getText() != null && !doc.getText().isBlank())
                .collect(Collectors.toList());

        // Step 4: If there are valid chunks, add them to the vector store for embedding
        if (!validChunks.isEmpty()) {
            vectorStore.add(validChunks);
        } else {
            // If no valid chunks are found, return a message indicating so
            return "No valid chunks to upsert.";
        }

        // Step 5: Return a success message after successfully uploading the PDF
        return "PDF uploaded successfully";
    }


    public String queryRAG(String userQuery) {

        // 1. Search for relevant documents from vector store
        List<Document> retrievedDocs = vectorStore.similaritySearch(userQuery);

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
