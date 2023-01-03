package com.neuro.userservice.controller;

import com.neuro.userservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final TokenService tokenService;

    @GetMapping("/registrationConfirm")
    public void confirmRegistration(String token) {
        tokenService.validateToken(token);
    }
}
