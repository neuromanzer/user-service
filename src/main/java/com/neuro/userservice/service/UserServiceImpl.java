package com.neuro.userservice.service;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.model.User;
import com.neuro.userservice.repository.UserRepository;
import com.neuro.userservice.wrapper.Response;
import com.neuro.userservice.wrapper.Violation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.neuro.userservice.enums.Status.EMPTY_REQUEST;
import static com.neuro.userservice.enums.Status.VALIDATION_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Validator validator;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Response create(UserDto userDto) {
        Response response = new Response();
        if (userDto == null) {
            response.setStatus(EMPTY_REQUEST);
            return response;
        }
        User user = new User();
        List<Violation> violations = getViolations(user);
        if (!violations.isEmpty()) {
            response.setStatus(VALIDATION_ERROR);
            response.setViolations(violations);
            return response;
        }
        userRepository.save(user);
        return null;
    }

    private List<Violation> getViolations(User user) {
        Set<ConstraintViolation<User>> constraints = validator.validate(user);
        List<Violation> violations = new ArrayList<>();
        if (!constraints.isEmpty()) {
            log.info("validation failed");
            constraints.forEach(x -> {
                violations.add(Violation.builder()
                        .className(x.getRootBeanClass().getSimpleName())
                        .propertyName(x.getPropertyPath().toString())
                        .message(x.getMessage())
                        .value(x.getInvalidValue())
                        .build());
            });
            return violations;
        }
        return violations;
    }
}
