package com.airtribe.rohit.newsaggregator.config;

import com.airtribe.rohit.newsaggregator.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Autowired
    private UserRepository _userRepository;

    @Bean
    public WebClient.Builder webClientCongif(){
        return WebClient.builder();
    }


    @Bean
    UserDetailsService userDetailsService() {
        return username -> _userRepository.findByUsername(username)
               ;
    }
}
