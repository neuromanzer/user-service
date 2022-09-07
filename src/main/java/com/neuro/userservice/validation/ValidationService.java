package com.neuro.userservice.validation;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.wrapper.Violation;

import java.util.List;

public interface ValidationService {
    List<Violation> getViolations(UserDto userDto);
}
