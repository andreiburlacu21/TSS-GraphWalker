package org.example.authapi.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Post {
    private String accountUsername;
    private String content;
}
