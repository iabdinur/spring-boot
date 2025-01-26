package com.iabdinur.ai.deepseek;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deepseek")
public class DeepseekAiConfig {

    @Value("${deepseek.api.url}")
    private String deepseekApiUrl;

    @Value("${deepseek.api.key}")
    private String deepseekApiKey;

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
