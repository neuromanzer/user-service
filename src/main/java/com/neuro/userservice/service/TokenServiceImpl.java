package com.neuro.userservice.service;

import com.neuro.userservice.model.User;
import com.neuro.userservice.model.VerificationToken;
import com.neuro.userservice.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final UserService userService;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void validateToken(String token) {
        log.info("TOKEN: {}", token);
        VerificationToken verificationToken = findByToken(token);
        log.info("verificationToken: {}", verificationToken);
        User user = verificationToken.getUser();
        log.info("user: {}", user);
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
    }
}
