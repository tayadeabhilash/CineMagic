package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iam")
public class IAMController {

    @PostMapping("/signup")
    public UserDto registerUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return null;
    }

    @PostMapping("/login") {
        return null;
    }

}
