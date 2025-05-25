package com.tech45degree.util;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SemanticChunker {

    // Configure maxTokens=800, overlapTokens=100, minTokens=200
    private final TokenTextSplitter splitter = new TokenTextSplitter(800,
            100,
            200,
            10000,
            true);

    /**
     * Splits each pdf Document into smaller token chunks.
     */
    public List<Document> chunkParagraphs(List<Document> paragraphs) {
        List<Document> chunks = new ArrayList<>();
        for (Document paraDoc : paragraphs) {
            // split returns List<Document> where each Document is a chunk of that paragraph
            chunks.addAll(splitter.apply(List.of(paraDoc)));
        }
        return chunks;
    }
}