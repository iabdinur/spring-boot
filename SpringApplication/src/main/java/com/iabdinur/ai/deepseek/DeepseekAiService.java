package com.iabdinur.ai.deepseek;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

public class DeepseekAiService {
    import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

    @Service
    public class DeepseekAiService {

        @Value("${deepseek.api.url}")
        private String apiUrl;

        @Value("${deepseek.api.key}")
        private String apiKey;

        private final RestTemplate restTemplate = new RestTemplate();

        public String callDeepSeekR1Api(String requestPayload) {
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");

            // Create the request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(requestPayload, headers);

            // Make the API call
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // Return the response body
            return response.getBody();
        }
    }
}
