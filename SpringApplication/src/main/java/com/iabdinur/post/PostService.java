package com.iabdinur.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostService {

    private final RestClient restClient;

    public PostService() {
        this.restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
    }

    public List<Post> getAllPosts() {
        return restClient.get().uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    public Post getPostById(Integer id) {
        return restClient.get().uri("/posts/{id}")
                .retrieve()
                .body(Post.class);
    }

    public void createPost(Post post) {
        restClient.post().uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .toBodilessEntity();
    }

    public void updatePost(Post post) {
        restClient.put().uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .toBodilessEntity();
    }

    public void deletePost(Integer id) {
        restClient.delete().uri("/posts/{id}")
                .retrieve()
                .toBodilessEntity();
    }
}
