package com.neuro.userservice.service;

import com.neuro.userservice.model.User;

public interface TokenService {
    void createVerificationToken(User user, String token);
}
