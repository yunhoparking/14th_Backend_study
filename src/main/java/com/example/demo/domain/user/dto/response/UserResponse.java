package com.example.demo.domain.user.dto.response;

import com.example.demo.domain.user.entity.User;

public record UserResponse(
        Long id,
        String email,
        String token
) {
    public static UserResponse from(User user, String token) {
        return new UserResponse(user.getId(), user.getEmail(), token);
    }
}