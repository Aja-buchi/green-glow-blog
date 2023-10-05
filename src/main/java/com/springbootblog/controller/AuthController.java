package com.springbootblog.controller;

import com.springbootblog.payload.JWTAuthResponse;
import com.springbootblog.payload.LoginDto;
import com.springbootblog.payload.RegisterDto;
import com.springbootblog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
//Customizes swagger API documentation
@Tag(
        name = "CRUD REST APIs for Auth Resource"
)
public class AuthController {
    @Autowired
    private AuthService authService;

    //Build a login REST API
    @PostMapping(value = {"/login", "signin"})
    //Customizes swagger API documentation
    @Operation(
            summary = "Login REST API",
            description = "Login REST API is used to authenticate a user before having access to the app "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    //Build a register REST API
    @PostMapping(value = {"/register", "signup"})
    //Customizes swagger API documentation
    @Operation(
            summary = "Register REST API",
            description = "Register REST API is used to register a user to the app "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
