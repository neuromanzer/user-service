package com.neuro.userservice.service;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.wrapper.Response;

public interface UserService {
    Response create(UserDto userDto);
}
