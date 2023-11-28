package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.service.IAMService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/iam")
public class IAMController {

    @Autowired
    IAMService iamService;

    @PostMapping("/signup")
    public UserDto registerUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return iamService.signUp(createUserRequest);
    }

    @PostMapping("/login")
    public UserDto signIn() {
        return iamService.signIn();
    }


    @PostMapping("/logout")
    public void logout() {
        iamService.logout();
    }

}
