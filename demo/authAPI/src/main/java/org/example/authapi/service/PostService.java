package org.example.authapi.service;

import lombok.RequiredArgsConstructor;
import org.example.authapi.model.Post;
import org.example.authapi.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPostsByUsername(String username) {
        return postRepository.getPostsByUsername(username);
    }

    public Post createPost(Post post) {
        return postRepository.createPost(post);
    }
}
