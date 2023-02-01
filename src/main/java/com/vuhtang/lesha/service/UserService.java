package com.vuhtang.lesha.service;

import com.vuhtang.lesha.model.User;

public interface UserService {
    void registerUser(User user);
    User findByUsername(String username);
}
