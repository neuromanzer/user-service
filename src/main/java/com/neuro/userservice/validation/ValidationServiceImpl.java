package com.neuro.userservice.validation;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.wrapper.Violation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    @Override
    public List<Violation> getViolations(UserDto userDto) {
        Set<ConstraintViolation<UserDto>> constraints = validator.validate(userDto);
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
