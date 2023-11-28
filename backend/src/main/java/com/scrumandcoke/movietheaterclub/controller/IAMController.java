package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.annotation.LoginRequired;
import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.LoginRequest;
import com.scrumandcoke.movietheaterclub.dto.UserSessionDetail;
import com.scrumandcoke.movietheaterclub.service.IAMService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v0/iam")
public class IAMController {

    @Autowired
    IAMService iamService;

    @PostMapping("/signup")
    public UserSessionDetail registerUser(@Valid @RequestBody CreateUserRequest createUserRequest, HttpServletResponse response) {

        UserSessionDetail userSessionDetail = iamService.signUp(createUserRequest);

        Cookie cookie = new Cookie("sid", userSessionDetail.getSessionId());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return userSessionDetail;
    }

    @PostMapping("/login")
    public UserSessionDetail signIn(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        UserSessionDetail userSessionDetail = iamService.signIn(loginRequest.getEmail(), loginRequest.getPassword());

        Cookie cookie = new Cookie("sid", userSessionDetail.getSessionId());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return userSessionDetail;
    }


    @GetMapping("/isAuthenticated")
    public UserSessionDetail isAuthenticated(@CookieValue("sid") String sessionId) {
        return iamService.isAuthenticated(sessionId);
    }


    @LoginRequired
    @PostMapping("/logout")
    public void logout() {
        iamService.logout();
    }
}
