package com.tech45degree.service;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

@Service
public class OpenAIService {

    final ChatModel chatModel;

    public OpenAIService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }



    public String sendMessage(String userMessage,String filePath) {

        var imageResource = new ClassPathResource(filePath);

        var message = new UserMessage(userMessage,
                new Media(MimeTypeUtils.IMAGE_PNG,imageResource));

        var  prompt = new Prompt(message,
                OpenAiChatOptions.builder()
                        .model(OpenAiApi.ChatModel.GPT_4_O.getValue())
                        .temperature(0.9)
        .build());

        ChatResponse chatResponse =  chatModel.call(prompt);

        return  chatResponse.getResult().getOutput().getText();
    }
}
