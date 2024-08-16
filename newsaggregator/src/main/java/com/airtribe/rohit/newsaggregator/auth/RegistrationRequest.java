package com.airtribe.rohit.newsaggregator.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "First Name is mandatory")
    @NotNull(message = "First Name is mandatory")
    private String firstname;
    @NotEmpty(message = "last Name is mandatory")
    @NotNull(message = "last Name is mandatory")
    private String lastname;

    @Email
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 5,max=15,message = "Password should be between 5 and 15 chars")
    private String password;




}
