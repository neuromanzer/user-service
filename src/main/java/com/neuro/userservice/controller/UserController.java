package com.neuro.userservice.controller;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.handler.MutationHandler;
import com.neuro.userservice.service.UserService;
import com.neuro.userservice.wrapper.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MutationHandler mutationHandler;

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody UserDto userDto) {
        log.info("userDto: {}", userDto);
        Response response = mutationHandler.handle(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public void update() {

    }

    @DeleteMapping("/delete")
    public void delete() {

    }

    @GetMapping("/read")
    public void read() {
        userService.getAll();
    }
}
