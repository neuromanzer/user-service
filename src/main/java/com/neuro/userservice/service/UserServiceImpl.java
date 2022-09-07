package com.neuro.userservice.service;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.model.User;
import com.neuro.userservice.repository.UserRepository;
import com.neuro.userservice.wrapper.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        userRepository.save(null);
        return null;
    }

    @Override
    public Response update(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
