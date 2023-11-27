package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public UserDto registerUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable @NotNull String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/allUsers")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PutMapping
    public void updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }
}
