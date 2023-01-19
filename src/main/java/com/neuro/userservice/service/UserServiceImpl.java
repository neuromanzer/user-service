package com.neuro.userservice.service;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.handler.event.OnRegistrationCompleteEvent;
import com.neuro.userservice.mapper.UserMapper;
import com.neuro.userservice.model.User;
import com.neuro.userservice.repository.UserRepository;
import com.neuro.userservice.validation.ValidationService;
import com.neuro.userservice.wrapper.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.neuro.userservice.enums.Status.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public Response create(UserDto userDto, HttpServletRequest request) {
        Response response = createResponse(userDto);
        response = validationService.validate(userDto, response);
        if (!ObjectUtils.isEmpty(response.getViolations())) {
            response.setStatus(VALIDATION_ERROR);
            return response;
        }
        if (userRepository.existsUserByEmail(userDto.getEmail())) {
            response.setStatus(USER_ALREADY_EXISTS);
            return response;
        }
        User user = mapper.toModel(userDto);
        String appUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toString();
        try {
            userRepository.save(user);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));
            response.setStatus(USER_CREATED);
            return response;
        } catch (Exception e) {
            response.setStatus(EMAIL_SERVICE_NOT_AVAILABLE);
            return response;
        }
    }

    @Override
    public Response update(Long id, UserDto userDto) {
        Response response = createResponse(userDto);
        response = validationService.validate(userDto, response);
        if (!response.getViolations().isEmpty()) return response;
        Optional<User> userFromDb = userRepository.findById(id);
        if (!userFromDb.isPresent()) {
            response.setStatus(USER_NOT_FOUND);
            return response;
        }
        //userRepository.save(user);
        response.setStatus(USER_UPDATED);
        return response;
    }

    @Override
    public Response delete(Long id) {
        Response response = new Response();
        try {
            userRepository.deleteById(id);
            response.setStatus(USER_DELETED);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            response.setStatus(NO_USER_FOR_DELETE);
            return response;
        }
        return response;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = mapper.toDto(user);
            userDtos.add(userDto);
        });
        return userDtos;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    private Response createResponse(UserDto userDto) {
        Response response = new Response();
        response.setDto(userDto);
        return response;
    }
}
