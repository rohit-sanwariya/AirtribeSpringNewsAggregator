package com.airtribe.rohit.newsaggregator.user;

import com.airtribe.rohit.newsaggregator.auth.RegistrationRequest;
import com.airtribe.rohit.newsaggregator.role.Role;
import com.airtribe.rohit.newsaggregator.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private  UserRepository userRepository;
    private final RoleService roleService;
    public UserService(RoleService roleService) {
        this.roleService = roleService;
    }


    public User createUser(User user){
        return userRepository.save(user);
    }


    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getUsers(Long id){
        return userRepository.findAll();
    }


    public User registerUser(RegistrationRequest request) {
        Role role = roleService.getRoleByName("USER");
        User user = User.builder()
                .username(request.getEmail())
                .lastname(request.getLastname())
                .firstname(request.getFirstname())
                .password(request.getPassword())
                .isAccountEnabled(true)
                .isNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialNonExpired(true)
                .roles(List.of(role))
                .build();
        return  userRepository.save(user);

    }


}
