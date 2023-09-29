package com.springbootblog.service;

import com.springbootblog.dto.LoginDto;
import com.springbootblog.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
