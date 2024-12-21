package com.fpt.identityservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserUpdateRequest {
    @Size(min = 8, message = "Mật khẩu phải ít nhất có 8 ký tự!")
    @NotBlank(message = "Mật khẩu không được bỏ trống!")
    @NotEmpty(message = "Mật khẩu không được bỏ trống!")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

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
