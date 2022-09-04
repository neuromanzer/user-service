package com.neuro.userservice.wrapper;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    private Status status;
    private UserDto dto;
    private List<Violation> violations;
}
