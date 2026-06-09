package com.example.demo.domain.post.dto.response;

import com.example.demo.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostAllResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostAllResponse from(Post post) {
        return new PostAllResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}

