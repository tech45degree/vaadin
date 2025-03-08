package com.tech45degree.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    @Autowired
    ChatModel chatModel;

    public String sendMessage(String userMessage) {

        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return chatModel.call(userMessage);
    }
}
