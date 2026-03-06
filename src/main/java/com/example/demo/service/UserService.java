package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.UserProfileResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final ResponseMapper responseMapper;

    public UserService(UserRepository userRepository,
                       CurrentUserService currentUserService,
                       ResponseMapper responseMapper) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.responseMapper = responseMapper;
    }

    public UserProfileResponse getCurrentUserProfile() {
        return responseMapper.toUserProfileResponse(currentUserService.getCurrentUser());
    }

    public UserProfileResponse updateCurrentUser(UserUpdateRequest request) {
        User currentUser = currentUserService.getCurrentUser();
        if (!currentUser.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        currentUser.setName(request.getName());
        currentUser.setEmail(request.getEmail());
        return responseMapper.toUserProfileResponse(userRepository.save(currentUser));
    }

    public void deleteCurrentUser() {
        User currentUser = currentUserService.getCurrentUser();
        userRepository.delete(currentUser);
    }
}
