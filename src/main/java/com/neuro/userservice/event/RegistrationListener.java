package com.neuro.userservice.event;

import com.neuro.userservice.service.TokenService;
import com.neuro.userservice.wrapper.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final RestTemplate restTemplate;
    private final TokenService tokenService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationToken(event.getUser(), token);
        Email email = Email
                .builder()
                .email(event.getUser().getEmail())
                .appUrl("url")
                .token(token)
                .message("")
                .build();
        HttpEntity<Email> request = new HttpEntity<>(email);
        ResponseEntity<Email> response = restTemplate.exchange("http://localhost:8081/api/sendEmail", HttpMethod.POST, request, Email.class);
    }

}
