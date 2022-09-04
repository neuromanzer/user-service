package com.neuro.userservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/create")
    public void create() {

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
