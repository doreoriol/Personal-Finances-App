package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserProfileResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserProfileResponse getCurrentUser() {
        return userService.getCurrentUserProfile();
    }

    @PutMapping("/me")
    public UserProfileResponse updateCurrentUser(@Valid @RequestBody UserUpdateRequest userRequest) {
        return userService.updateCurrentUser(userRequest);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrentUser() {
        userService.deleteCurrentUser();
    }
}
