package com.iabdinur.ai.deepseek;

import com.iabdinur.ai.openai.JavaFrameworkRank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/deepseek")
public class DeepseekAiController {

    private final DeepseekAiService deepseekAiService;

    public DeepseekAiController(DeepseekAiService deepseekAiService) {
        this.deepseekAiService = deepseekAiService;
    }

    @GetMapping("chat")
    public String chat(@RequestParam String message) {
        return deepseekAiService.getChatResponse(message);
    }
}