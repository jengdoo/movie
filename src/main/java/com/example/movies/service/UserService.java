package com.example.movies.service;

import com.example.movies.dto.request.IntrospectRequest;
import com.example.movies.dto.request.UserLoginRequest;
import com.example.movies.dto.request.UserRegisterRequest;
import com.example.movies.dto.response.IntrospectResponse;
import com.example.movies.dto.response.UserLoginResponse;
import com.example.movies.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse createUser(UserRegisterRequest userRegisterRequest);
    UserResponse updateUser(Long id, UserRegisterRequest userRegisterRequest);
    void deleteUser(Long id);
    UserLoginResponse login(UserLoginRequest userLoginRequest);
    IntrospectResponse introspect(IntrospectRequest introspectRequest);
}
