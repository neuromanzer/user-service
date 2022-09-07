package com.neuro.userservice.handler;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.model.User;
import com.neuro.userservice.service.UserService;
import com.neuro.userservice.validation.ValidationService;
import com.neuro.userservice.wrapper.Response;
import com.neuro.userservice.wrapper.Violation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.neuro.userservice.enums.Status.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MutationHandlerImpl implements MutationHandler {

    private final UserService userService;
    private final ValidationService validationService;
    //private final UserMapper userMapper;

    @Override
    public Response handle(UserDto userDto) {
        Response response = new Response();
        if (userDto == null) {
            response.setStatus(EMPTY_REQUEST);
            return response;
        }
        response.setDto(userDto);
        List<Violation> violations = validationService.getViolations(userDto);
        if (!violations.isEmpty()) {
            response.setStatus(VALIDATION_ERROR);
            response.setViolations(violations);
            return response;
        }
        //TODO mapper
        User user = new User();
        if (userService.create(user) != null) {
            response.setStatus(USER_CREATED);
            return response;
        }
        return null;
    }
}
