package com.iabdinur.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ai")
public class AiController {

    private final ChatClient chatClient;

    public AiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("chat")
    public String chat(){
        var content = chatClient.prompt()
                .user("what are the best teams in the english premier league. I want the output to be in a list format")
                .call()
                .content();
        return content;
    }
}
