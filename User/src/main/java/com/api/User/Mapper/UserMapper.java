package com.api.User.Mapper;


import com.api.User.Dto.UserRequest;
import com.api.User.Dto.UserResponse;
import com.api.User.Model.Users;

public interface UserMapper {
    Users toEntity(UserRequest userRequest);
    UserResponse toDto(Users user);
}
