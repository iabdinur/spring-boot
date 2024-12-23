package com.iabdinur.post;

import com.iabdinur.post.jsonplaceholder.JSONPlaceHolderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;
    private final JSONPlaceHolderService jsonPlaceholderService;

    public PostController(PostService postService, JSONPlaceHolderService jsonPlaceholderService) {
        this.postService = postService;
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return jsonPlaceholderService.getAllPosts();
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable("id") Integer id) {
        return jsonPlaceholderService.getPostById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(Post post) {
        jsonPlaceholderService.createPost(post);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePost(@PathVariable("id") Integer id,
                           @RequestBody Post post) {
        jsonPlaceholderService.updatePost(id, post);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable("id") Integer id) {
        jsonPlaceholderService.deletePostById(id);
    }

}
