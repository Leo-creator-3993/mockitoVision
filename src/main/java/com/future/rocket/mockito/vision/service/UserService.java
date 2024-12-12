package com.future.rocket.mockito.vision.service;

import com.future.rocket.mockito.vision.model.User;
import com.future.rocket.mockito.vision.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserFullName(Long userId) {
        User user = userRepository.findById(userId);
        return user != null ? user.getFirstName() + " " + user.getLastName() : "Unknown user";
    }
}
