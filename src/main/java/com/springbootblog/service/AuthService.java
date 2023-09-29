package com.springbootblog.service;

import com.springbootblog.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
