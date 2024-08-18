package com.airtribe.rohit.newsaggregator.auth;

import com.airtribe.rohit.newsaggregator.models.ResponseModel;
import com.airtribe.rohit.newsaggregator.user.UserRegistrationResponseDto;
import com.airtribe.rohit.newsaggregator.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserRegistrationResponseDto registerUser(@RequestBody RegistrationRequest request){
        return service.registerUser(request);
    }

    @PostMapping("/signin")
    public ResponseModel registerUser(@RequestBody LoginRequest request){
        return service.loginUser(request);
    }
}