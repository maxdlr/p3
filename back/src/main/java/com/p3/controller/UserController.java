package com.p3.controller;

import com.p3.dto.api.AddApiResponse;
import com.p3.dto.api.BrowseApiResponse;
import com.p3.dto.api.ErrorApiResponse;
import com.p3.dto.api.ReadApiResponse;
import com.p3.exception.ApiResourceNotFoundException;
import com.p3.model.UserEntity;
import com.p3.persistence.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Object> list(){
        return new BrowseApiResponse<>("users", userRepository.findAll()).get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable int id){
        return userRepository.findById(id)
                .map(user -> new ReadApiResponse<>(user).get())
                .orElseThrow(() -> new ApiResourceNotFoundException("This user doesn't exist."));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody UserEntity user){
        userRepository.save(user);
        return new AddApiResponse("User has been created successfully.").get();
    }
}
