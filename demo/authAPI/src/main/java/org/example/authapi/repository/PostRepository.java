package org.example.authapi.repository;

import org.example.authapi.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private static final List<Post> existingPosts = new ArrayList<>(List.of(
            Post.builder()
                    .accountUsername("marian")
                    .content("Content1")
                    .build(),

            Post.builder()
                    .accountUsername("marian")
                    .content("Content2")
                    .build(),

            Post.builder()
                    .accountUsername("marian")
                    .content("Content3")
                    .build(),

            Post.builder()
                    .accountUsername("marian")
                    .content("Content4")
                    .build()
    ));


    public List<Post> getPostsByUsername(String username) {
        return existingPosts.stream()
                .filter(post -> post.getAccountUsername().equals(username))
                .toList();
    }

    public Post createPost(Post post) {
        existingPosts.add(post);
        return post;
    }
}
