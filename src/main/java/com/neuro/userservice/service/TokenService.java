package com.neuro.userservice.service;

import com.neuro.userservice.model.User;
import com.neuro.userservice.model.VerificationToken;

public interface TokenService {
    void createVerificationToken(User user, String token);

    VerificationToken findByToken(String token);

    void validateToken(String token);
}
