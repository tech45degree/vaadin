package com.tech45degree.service;

import io.github.thoroldvix.api.Transcript;
import io.github.thoroldvix.api.TranscriptContent;
import io.github.thoroldvix.api.TranscriptList;
import io.github.thoroldvix.api.YoutubeTranscriptApi;
import io.github.thoroldvix.internal.TranscriptApiFactory;
import org.springframework.stereotype.Component;

@Component
public class YoutubeTranscripter {

    public String getTranscript(String videoId) {
        try {
            // Create a new default YoutubeTranscriptApi instance
            YoutubeTranscriptApi youtubeTranscriptApi = TranscriptApiFactory.createDefault();

            // Retrieve all available transcripts for the given video ID
            TranscriptList transcriptList = youtubeTranscriptApi.listTranscripts(videoId);

            // Iterate over the transcript list
            for (Transcript transcript : transcriptList) {
                // Fetch the transcript lines
                TranscriptContent transcriptContent = transcript.translate("en").fetch();

                // Build the transcript text
                StringBuilder transcriptText = new StringBuilder();
                for (TranscriptContent.Fragment line : transcriptContent.getContent()) {
                    transcriptText.append(line.getText()).append("\n");
                }

                return transcriptText.toString();
            }

            return "No transcripts available for this video.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to retrieve transcript: " + e.getMessage();
        }
    }

}
