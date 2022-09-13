package com.neuro.userservice.service;

import com.neuro.userservice.model.User;
import com.neuro.userservice.model.VerificationToken;
import com.neuro.userservice.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
    }
}
