package com.neuro.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neuro.userservice.validation.annotation.ValidateEmail;
import com.neuro.userservice.validation.annotation.ValidatePassword;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidatePassword
public class UserDto {
    /**
     * имя пользователя
     */
    private String username;
    /**
     * пароль пользователя
     */
    private String password;
    /**
     * повтор введенного пароля
     */
    private String matchedPassword;
    /**
     * email пользователя
     */
    @ValidateEmail
    private String email;
    /**
     * дата рождения пользователя
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;
}
