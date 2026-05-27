package com.example.demo.domain.user.dto.response;

import com.example.demo.domain.user.entity.User;

public record UserResponse(
        Long id,
        String email
)
{
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail());
    }
}