package com.neuro.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neuro.userservice.validation.ValidateEmail;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    /**
     * Имя пользователя
     */
    private String username;
    /**
     * Пароль
     */
    private String password;
    /**
     * email
     */
    @ValidateEmail
    private String email;
    /**
     * birthdate
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;
}
