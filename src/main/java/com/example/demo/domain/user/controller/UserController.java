package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.dto.request.LoginRequest;
import com.example.demo.domain.user.dto.request.SignupRequest;
import com.example.demo.domain.user.dto.response.UserResponse;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.global.common.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public SuccessResponse<UserResponse> signup(@RequestBody @Valid SignupRequest request) {
        return SuccessResponse.created(userService.signup(request),"회원가입 완료");
    }

    @PostMapping("/login")
    public SuccessResponse<UserResponse> login(@RequestBody @Valid LoginRequest request) {
        return SuccessResponse.ok(userService.login(request),"로그인 완료");
    }
}