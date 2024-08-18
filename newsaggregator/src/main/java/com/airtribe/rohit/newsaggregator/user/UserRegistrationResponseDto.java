package com.airtribe.rohit.newsaggregator.user;

import com.airtribe.rohit.newsaggregator.role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserRegistrationResponseDto {
    private long id;
    private List<Role> roles;
    private String username;
    private String firstname;
    private String lastname;
    private Token token;

}
