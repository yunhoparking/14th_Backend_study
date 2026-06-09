package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.request.LoginRequest;
import com.example.demo.domain.user.dto.request.SignupRequest;
import com.example.demo.domain.user.dto.response.UserResponse;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public UserResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException(ErrorCode.USER_ALREADY_EXISTS);
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.create(request.email(), encodedPassword);
        userRepository.save(user);
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail());
        return UserResponse.from(user, token);
    }

    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email());
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail());
        return UserResponse.from(user, token);
    }
}