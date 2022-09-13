package com.neuro.userservice.wrapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
    private String email;
    private String appUrl;
    private String token;
    private String message;
}
