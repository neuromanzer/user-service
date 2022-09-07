package com.neuro.userservice.handler;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.wrapper.Response;

public interface MutationHandler {
    Response handle(UserDto userDto);
}
