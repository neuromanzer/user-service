package com.neuro.userservice.service;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.model.User;
import com.neuro.userservice.wrapper.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    Response create(UserDto userDto, HttpServletRequest request);

    Response update(Long id, UserDto userDto);

    Response delete(Long id);

    List<UserDto> getAll();

    void saveRegisteredUser(User user);
}
