package com.airtribe.rohit.newsaggregator.controller;

import com.airtribe.rohit.newsaggregator.auth.RegistrationRequest;
import com.airtribe.rohit.newsaggregator.user.User;
import com.airtribe.rohit.newsaggregator.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationRequest request){
        return service.registerUser(request);
    }
}