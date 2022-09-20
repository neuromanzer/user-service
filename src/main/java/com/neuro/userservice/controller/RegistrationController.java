package com.neuro.userservice.controller;

import com.neuro.userservice.model.User;
import com.neuro.userservice.model.VerificationToken;
import com.neuro.userservice.repository.VerificationTokenRepository;
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
        log.info("TOKEN: {}", token);
        VerificationToken verificationToken = tokenService.findByToken(token);
        log.info("verificationToken: {}", verificationToken);
        User user = verificationToken.getUser();
        log.info("user: {}", user);
    }
}
