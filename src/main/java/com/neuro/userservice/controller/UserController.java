package com.neuro.userservice.controller;

import com.neuro.userservice.dto.UserDto;
import com.neuro.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void create(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }

    @GetMapping("/read")
    public void read() {

    }

    @GetMapping("/update")
    public void update() {

    }

    @DeleteMapping("/delete")
    public void delete() {

    }
}
