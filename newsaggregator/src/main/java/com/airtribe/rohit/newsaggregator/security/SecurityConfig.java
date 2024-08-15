package com.airtribe.rohit.newsaggregator.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c->c.ignoringRequestMatchers("/h2-console/**"))
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/h2-console/**")
                                .permitAll()
                                .requestMatchers("/public/**")
                                .permitAll().anyRequest().authenticated()
                )
                .headers(h->h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
        ;
        return http.build();
    }

}
