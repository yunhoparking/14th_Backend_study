package com.example.demo.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest (
    @NotBlank(message = "제목은 필수 값 입니다")
    String title,

    @NotBlank(message = "내용은 필수 값 입니다")
    String content,

    @NotBlank(message = "작성자는  필수 값 입니다")
    String author
){}
