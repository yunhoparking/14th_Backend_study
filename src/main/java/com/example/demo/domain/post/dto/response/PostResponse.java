package com.example.demo.domain.post.dto.response;

import com.example.demo.domain.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PostResponse (
    Long id,
    String title,
    String content,
    String author,
    @JsonProperty("Created_at")
    LocalDateTime createdAt,
    @JsonProperty("updated_at")
    LocalDateTime updatedAt
)

{
    public static PostResponse from(Post post){
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
