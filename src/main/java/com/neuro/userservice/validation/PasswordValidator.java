package com.neuro.userservice.validation;

import com.neuro.userservice.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Service
public class PasswordValidator implements ConstraintValidator<ValidatePassword, Object> {

    @Override
    public void initialize(ValidatePassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDto userDto = (UserDto) obj;
        return userDto.getPassword().equals(userDto.getMatchedPassword());
    }
}
