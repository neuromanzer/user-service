package com.neuro.userservice.mapper;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserDto userDto);

    UserDto toDto(User user);
}
