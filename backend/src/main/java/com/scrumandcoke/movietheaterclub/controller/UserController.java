package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email) throws GlobalException {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/allUsers")
    public List<UserDto> getUsers() throws GlobalException {
        return userService.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody UserDto user) throws GlobalException {
        userService.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDto userDto) throws GlobalException {
        userService.updateUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) throws GlobalException {
        userService.deleteUser(userId);
    }
}
