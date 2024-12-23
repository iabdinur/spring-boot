package com.iabdinur.post.jsonplaceholder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class JSONPlaceholderConfig {

    @Bean("rest-client")
    JSONPlaceholderService jsonPlaceholderRestClientService(){
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build().
                createClient(JSONPlaceholderService.class);
    }

    @Primary
    @Bean("web-client")
    JSONPlaceholderService jsonPlaceholderWebClientService(){
        WebClient restClient = WebClient.create("https://jsonplaceholder.typicode.com/");
        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(restClient))
                .build().createClient(JSONPlaceholderService.class);
    }
}
