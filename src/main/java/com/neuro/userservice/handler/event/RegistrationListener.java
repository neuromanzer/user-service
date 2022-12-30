package com.neuro.userservice.handler.event;

import com.neuro.userservice.service.TokenService;
import com.neuro.userservice.wrapper.Email;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final RestTemplate restTemplate;
    private final TokenService tokenService;
    @Value("${email.service.url}")
    private String url;

    @SneakyThrows
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws Exception {
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationToken(event.getUser(), token);
        Email email = Email
                .builder()
                .email(event.getUser().getEmail())
                .appUrl(event.getAppUrl() + "/registrationConfirm?token=" + token)
                .subject("Registration")
                .message("Welcome to the void....")
                .build();
        HttpEntity<Email> request = new HttpEntity<>(email);
        try {
            restTemplate.exchange(url, HttpMethod.POST, request, Email.class);
        } catch (Exception e) {
            throw new Exception("Сервис отправки email не работает");
        }
    }
}
