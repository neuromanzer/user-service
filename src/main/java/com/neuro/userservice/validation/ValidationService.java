package com.neuro.userservice.validation;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.wrapper.Response;

public interface ValidationService {
    Response validate(UserDto userDto);
}
