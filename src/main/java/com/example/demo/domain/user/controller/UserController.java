package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.dto.request.LoginRequest;
import com.example.demo.domain.user.dto.request.SignupRequest;
import com.example.demo.domain.user.dto.response.UserResponse;
import com.example.demo.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody @Valid SignupRequest request) {
        return userService.signup(request);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }
}