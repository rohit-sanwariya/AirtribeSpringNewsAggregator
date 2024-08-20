package com.airtribe.rohit.newsaggregator.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class AuthManagerConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{

        return config.getAuthenticationManager(

        );
    }
}
