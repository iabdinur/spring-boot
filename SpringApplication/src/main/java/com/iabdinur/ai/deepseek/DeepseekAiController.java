package com.iabdinur.ai.deepseek;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deepseek")
public class DeepseekAiController {

    private DeepseekAiService deepSeekAiService;

    @PostMapping("/call")
    public String callDeepSeekR1Api(@RequestBody String requestPayload) {
        return deepSeekAiService.callDeepSeekR1Api(requestPayload);
    }
}
