package com.fpt.identityservice.controller;

import com.fpt.identityservice.dto.request.UserCreateRequest;
import com.fpt.identityservice.dto.request.UserUpdateRequest;
import com.fpt.identityservice.schema.ServiceResponse;
import com.fpt.identityservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ServiceResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        var serviceResponse = userService.createUser(request);
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @GetMapping
    public ResponseEntity<ServiceResponse> getUsers() {
        var serviceResponse = userService.getUsers();
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ServiceResponse> getUser(@PathVariable("userId") String userId) {
        var serviceResponse = userService.getUser(userId);
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ServiceResponse> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        var serviceResponse = userService.updateUser(userId, request);
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ServiceResponse> deleteUser(@PathVariable("userId") String userId) {
        var serviceResponse = userService.deleteUser(userId);
        return new ResponseEntity<>(serviceResponse, serviceResponse.getStatusCode());
    }

}
