package com.springbootblog.service;

import com.springbootblog.payload.LoginDto;
import com.springbootblog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
