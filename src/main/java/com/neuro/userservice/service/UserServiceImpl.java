package com.neuro.userservice.service;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.model.User;
import com.neuro.userservice.repository.UserRepository;
import com.neuro.userservice.validation.ValidationService;
import com.neuro.userservice.wrapper.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.neuro.userservice.enums.Status.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;

    @Override
    @Transactional
    public Response create(UserDto userDto) {
        Response response = validationService.validate(userDto);
        if (!response.getViolations().isEmpty()) return response;
        User user = new User();
        userRepository.save(user);
        response.setStatus(USER_CREATED);
        return response;
    }

    @Override
    public Response update(Long id, UserDto userDto) {
        Response response = validationService.validate(userDto);
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
    public List<User> getAll() {
        return null;
    }
}
