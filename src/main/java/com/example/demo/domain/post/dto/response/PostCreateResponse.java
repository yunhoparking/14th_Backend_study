package com.example.demo.domain.post.dto.response;

import com.example.demo.domain.post.entity.Post;
import java.time.LocalDateTime;

public record PostCreateResponse (
    String title,
    String content,
    String author,
    LocalDateTime createAt) {

    public static PostCreateResponse of(Post post) {
        return new PostCreateResponse(
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt()
        );
    }
}
