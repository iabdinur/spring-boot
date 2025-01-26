package com.iabdinur.ai.deepseek;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DeepSeekChatClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

    public DeepSeekChatClient(RestTemplate restTemplate, DeepseekApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiProperties.getUrl();
        this.apiKey = apiProperties.getKey();
    }

    public String sendChatMessage(String message) {
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Create the request body
        String requestBody = "{\"message\":\"" + message + "\"}";

        // Create the request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the API call
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl + "/chat",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Return the response body
        return response.getBody();
    }
}