package com.future.rocket.mockito.vision.repository;

import com.future.rocket.mockito.vision.model.User;

public interface UserRepository {
    User findById(Long id);
}
