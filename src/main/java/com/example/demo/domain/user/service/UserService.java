package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.request.LoginRequest;
import com.example.demo.domain.user.dto.request.SignupRequest;
import com.example.demo.domain.user.dto.response.UserResponse;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.create(request.email(), encodedPassword);
        userRepository.save(user);
        return UserResponse.from(user);
    }

    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email());
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }
        return UserResponse.from(user);
    }
}