package com.neuro.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neuro.userservice.validation.annotation.ValidateEmail;
import com.neuro.userservice.validation.annotation.ValidatePassword;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "password must not be null or empty")
    private String password;
    /**
     * повтор введенного пароля
     */
    @NotEmpty(message = "matchedPassword must not be null or empty")
    private String matchedPassword;
    /**
     * email пользователя
     */
    @ValidateEmail
    @NotEmpty(message = "email must not be null or empty")
    private String email;
    /**
     * дата рождения пользователя
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;
}
