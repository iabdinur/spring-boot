package com.iabdinur.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("you are a software developer who has knowledge about various programming languages")
                .build();
    }

    @Bean
    public OpenAiAudioApi openAiAudioApi() {
        return new OpenAiAudioApi(openAiApiKey);
    }

}