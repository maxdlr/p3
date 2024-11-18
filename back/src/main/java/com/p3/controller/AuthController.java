package com.p3.controller;

import com.p3.dto.AuthResponseDto;
import com.p3.dto.LoginDto;
import com.p3.dto.RegisterDto;
import com.p3.exception.ApiBadPostRequestException;
import com.p3.exception.ApiResourceNotFoundException;
import com.p3.model.RoleEntity;
import com.p3.model.UserEntity;
import com.p3.persistence.RoleRepository;
import com.p3.persistence.UserRepository;
import com.p3.response.ReadApiResponse;
import com.p3.response.RegisterApiResponse;
import com.p3.security.JwtAuthenticationFilter;
import com.p3.security.JwtGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public AuthController(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtGenerator jwtGenerator,
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginDto loginDto) {

        if (!userRepository.existsByEmail(loginDto.getEmail())) {
            throw new ApiBadPostRequestException("This email address does not exist");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ApiBadPostRequestException("email is taken");
        }
        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        RoleEntity role = roleRepository.findByName("USER").isPresent() ? roleRepository.findByName("USER").get() : null;
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        return new RegisterApiResponse(
                "User successfully registered",
                jwtGenerator.generateToken(authentication)
        ).get();
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> me(HttpServletRequest request) {
        String token = jwtAuthenticationFilter.getJwtFromRequest(request);
        String email = jwtGenerator.getEmailFromToken(token);
        return new ResponseEntity<>(userRepository.findByEmail(email).get(), HttpStatus.OK);
    }
}
