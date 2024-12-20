package com.fpt.identityservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserCreateRequest {
    @Email(message = "Username must be in email form!")
    @NotBlank(message = "Username cannot be blank!")
    @NotEmpty(message = "Username cannot be empty!")
    private String username;
    @Size(min = 8, message = "Password must be at least 8 characters!")
    @NotBlank(message = "Password cannot be blank!")
    @NotEmpty(message = "Password cannot be empty!")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
