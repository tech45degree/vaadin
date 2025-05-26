package com.tech45degree.configuration;

import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.ai.vectorstore.pinecone.PineconeVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.vectorstore.VectorStore;

@Configuration
public class AIConfig {


    // OpenAI API Configuration
    // ─────────────────────────────────────────
    @Bean
    public OpenAiApi openAiApi(
            @Value("${spring.ai.openai.api-key}") String apiKey) {
        return OpenAiApi.builder()
                .apiKey(apiKey)
                .build();
    }

    // Pinecone Vector Store Configuration
    // ─────────────────────────────────────────
    @Bean
    public VectorStore pineconeVectorStore(
            EmbeddingModel embeddingModel,
            @Value("${spring.ai.vectorstore.pinecone.api-key}") String apiKeyPinecone,
            @Value("${spring.ai.vectorstore.pinecone.index-name}") String indexName) {

        return PineconeVectorStore.builder(embeddingModel)
                .apiKey(apiKeyPinecone)
                .indexName(indexName)
                .build();
    }

    // Embedding Model Configuration
    // ─────────────────────────────────────────
    @Bean
    public EmbeddingModel embeddingModel(OpenAiApi openAiApi) {
        return new OpenAiEmbeddingModel(openAiApi, MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder()
                        .model("text-embedding-3-small")
                        .build(), RetryUtils.DEFAULT_RETRY_TEMPLATE);
    }

    // Chat Model Configuration
    // ─────────────────────────────────────────
    @Bean
    public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi,OpenAiChatOptions openAiChatOptions) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(openAiChatOptions)
                .build();
    }

    // OpenAI Chat Options Configuration
    // ───────────────────────────────────────
    @Bean
    public OpenAiChatOptions openAiChatOptions()
    {
        return OpenAiChatOptions.builder()
                .model("gpt-3.5-turbo")
                .temperature(0.1)
                .build();
    }


}