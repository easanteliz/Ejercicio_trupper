package com.home.trupper.rest.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.home.trupper.rest.auth.AuthorizationResponse;
import com.home.trupper.rest.auth.UserRequest;
import com.home.trupper.rest.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity <AuthorizationResponse>login(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authService.login(userRequest));
    }

    @PostMapping(value="register")
    public ResponseEntity <AuthorizationResponse> register(@RequestBody UserRequest userRequest) throws JsonProcessingException {

        return ResponseEntity.ok(authService.register(userRequest));
    }

}