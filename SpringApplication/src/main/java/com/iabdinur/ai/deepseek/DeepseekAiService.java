package com.iabdinur.ai.deepseek;

import org.springframework.stereotype.Service;

@Service
public class DeepseekAiService {

    private final DeepSeekChatClient deepSeekChatClient;

    public DeepseekAiService(DeepSeekChatClient deepSeekChatClient) {
        this.deepSeekChatClient = deepSeekChatClient;
    }

    public String getChatResponse(String message) {
        return deepSeekChatClient.sendChatMessage(message);
    }
}