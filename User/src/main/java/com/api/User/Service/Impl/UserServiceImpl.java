package com.api.User.Service.Impl;


import com.api.User.Dto.UserRequest;
import com.api.User.Dto.UserResponse;
import com.api.User.Exception.InvalidEmailException;
import com.api.User.Exception.UserNotFoundException;
import com.api.User.Mapper.UserMapper;
import com.api.User.Model.Users;
import com.api.User.Repository.UserRepository;
import com.api.User.Service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackCreateUser")
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new InvalidEmailException("Email is already in use");
        }

        Users user = userMapper.toEntity(userRequest);
        Users savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackUpdateUser")
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setUpdatedAt(LocalDateTime.now());

        Users updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public UserResponse fallbackCreateUser(UserRequest userRequest, Throwable throwable) {
        return UserResponse.builder()
                .fullName("Fallback user")
                .email("Fallback email")
                .build();
    }

    public UserResponse fallbackUpdateUser(Long id, UserRequest userRequest, Throwable throwable) {
        return UserResponse.builder()
                .fullName("Fallback user")
                .email("Fallback email")
                .build();
    }


}