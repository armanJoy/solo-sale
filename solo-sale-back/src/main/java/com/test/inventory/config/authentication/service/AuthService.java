package com.test.inventory.config.authentication.service;

import com.test.inventory.dto.request.user.LoginRequest;
import com.test.inventory.dto.response.user.JwtResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);
}