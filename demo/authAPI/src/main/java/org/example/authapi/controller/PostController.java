package org.example.authapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.authapi.model.Post;
import org.example.authapi.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/get-posts/{username}")
    public ResponseEntity<List<Post>> getPostsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(postService.getPostsByUsername(username));
    }

    @PostMapping("/add-post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }
}
