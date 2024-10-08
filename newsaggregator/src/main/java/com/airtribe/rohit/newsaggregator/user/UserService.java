package com.airtribe.rohit.newsaggregator.user;

import com.airtribe.rohit.newsaggregator.auth.LoginRequest;
import com.airtribe.rohit.newsaggregator.auth.RegistrationRequest;
import com.airtribe.rohit.newsaggregator.models.ResponseModel;
import com.airtribe.rohit.newsaggregator.role.Role;
import com.airtribe.rohit.newsaggregator.services.JwtTokenService;
import com.airtribe.rohit.newsaggregator.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;
    public UserService(RoleService roleService, UserDetailsService userDetailsService) {
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenService jwtTokenService;

    public User createUser(User user){
        return userRepository.save(user);
    }

    @Value("${jwt.expiration}")
    private long expirationTime;

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getUsers(Long id){
        return userRepository.findAll();
    }


    public UserRegistrationResponseDto registerUser(RegistrationRequest request) {
        Role role = roleService.getRoleByName("USER");
        if(role == null){
            
            role = roleService.createRole("USER");
        }
        String token = jwtTokenService.createToken(new HashMap<>(),request.getEmail());
        Token t = Token.builder().token(token).expiresAt(new Date(System.currentTimeMillis()+expirationTime)).build();

        User user = User.builder()
                .username(request.getEmail())
                .lastname(request.getLastname())
                .firstname(request.getFirstname())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAccountEnabled(true)
                .isNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialNonExpired(true)
                .role(role)
                .build();
        user.setTokens(new ArrayList<>());
        t.setUser(user);

        user.getTokens().add(t);
        userRepository.save(user);
        tokenRepository.save(t);

        return  UserRegistrationResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .role(user.getRole())
                .token(t)
                .username(user.getUsername())
                .build();

    }


    public ResponseModel loginUser(LoginRequest request) {
        String name =  request.getUsername();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(name,request.getPassword())
        );
        User user = userRepository.findByUsername(name);
        if(user != null ){

            UserRegistrationResponseDto data =  UserRegistrationResponseDto.builder()
                    .id(user.getId())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .role(user.getRole())
                    .username(user.getUsername())

                    .build();
            Token token = Token
                    .builder()
                    .token(
                            jwtTokenService
                                    .createToken(new HashMap<>(),user.getUsername())
                    )
                    .user(user)
                    .build();
            data.setToken(token);
            tokenRepository.save(token);
            return ResponseModel.builder().data(data).message("Login Success").httpStatus(HttpStatus.OK).build();


        }
        return ResponseModel.builder().data(null).httpStatus(HttpStatus.BAD_REQUEST).message("Invalid credential").build();

    }


    public Optional<User> getUserByName(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public User updateUser(Optional<User> user) {
        return userRepository.save(user.get());
    }
}
