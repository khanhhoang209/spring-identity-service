package com.fpt.identityservice.service;

import com.fpt.identityservice.dto.request.UserCreateRequest;
import com.fpt.identityservice.dto.request.UserUpdateRequest;
import com.fpt.identityservice.entity.User;
import com.fpt.identityservice.repository.UserRepository;
import com.fpt.identityservice.schema.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ServiceResponse createUser(UserCreateRequest request) {
        var serviceResponse = new ServiceResponse();

        var user = new User();
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());

        userRepository.save(user);

        return serviceResponse
                .setSucceeded(true)
                .setStatusCode(HttpStatus.CREATED)
                .setResponseCode(HttpStatus.CREATED.value())
                .addDetail("message", "Registered successfully!");
    }

    public ServiceResponse getUsers() {
        var serviceResponse = new ServiceResponse();
        List<User> users = userRepository.findAll();

        var data = new HashMap<String, Object>();
        data.put("users", users);

        serviceResponse
                .setSucceeded(true)
                .setStatusCode(HttpStatus.OK)
                .setResponseCode(HttpStatus.OK.value())
                .addDetail("message", "Get list users successfully!")
                .addDetail("data", data);
        return serviceResponse;
    }

    public ServiceResponse getUser(String id) {
        var serviceResponse = new ServiceResponse();
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NullPointerException("User not found!");
        }

        var data = new LinkedHashMap<String, Object>();
        data.put("user", user);

        return serviceResponse
                .setSucceeded(true)
                .setStatusCode(HttpStatus.OK)
                .setResponseCode(HttpStatus.OK.value())
                .addDetail("message", "Get user successfully!")
                .addDetail("data", data);
    }

    public ServiceResponse updateUser(String userId, UserUpdateRequest request) {
        var serviceResponse = new ServiceResponse();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new NullPointerException("User not found!");
        }

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());

        return serviceResponse
                .setSucceeded(true)
                .setStatusCode(HttpStatus.OK)
                .setResponseCode(HttpStatus.OK.value())
                .addDetail("message", "Updated user successfully!");
    }

    public ServiceResponse deleteUser(String userId) {
        var serviceResponse = new ServiceResponse();
        var user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new NullPointerException("User not found!");
        }

        userRepository.delete(user);

        return serviceResponse
                .setSucceeded(true)
                .setStatusCode(HttpStatus.NO_CONTENT)
                .setResponseCode(HttpStatus.NO_CONTENT.value())
                .addDetail("message", "Deleted user successfully!");
    }
}
