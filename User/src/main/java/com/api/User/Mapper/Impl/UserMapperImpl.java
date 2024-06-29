package com.api.User.Mapper.Impl;


import com.api.User.Dto.UserRequest;
import com.api.User.Dto.UserResponse;
import com.api.User.Mapper.UserMapper;
import com.api.User.Model.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapperImpl implements UserMapper {

    public Users toEntity(UserRequest userRequest) {
        return Users.builder()
                .fullName(userRequest.getFullName())
                .email(userRequest.getEmail())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UserResponse toDto(Users user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .isActive(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}