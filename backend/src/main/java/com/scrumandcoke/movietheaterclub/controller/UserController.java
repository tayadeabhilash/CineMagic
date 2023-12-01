package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.annotation.LoginRequired;
import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.dto.UserSessionDetail;
import com.scrumandcoke.movietheaterclub.enums.MemberType;
import com.scrumandcoke.movietheaterclub.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v0/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public UserDto registerUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @LoginRequired
    @PostMapping("me/upgradeMembership")
    public UserDto upgradeMembership(@AuthenticationPrincipal UserSessionDetail userSessionDetail) {
        return userService.updateMemberType(userSessionDetail.getUserId(), MemberType.PREMIUM);
    }

    @PostMapping("me/downgradeMembership")
    @LoginRequired
    public UserDto downgradeMembership(@AuthenticationPrincipal UserSessionDetail userSessionDetail) {
        return userService.updateMemberType(userSessionDetail.getUserId(), MemberType.REGULAR);
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
