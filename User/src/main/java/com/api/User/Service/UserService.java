package com.api.User.Service;


import com.api.User.Dto.UserRequest;
import com.api.User.Dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}