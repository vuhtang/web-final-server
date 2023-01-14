package com.vuhtang.lesha.util;

import com.vuhtang.lesha.model.User;
import com.vuhtang.lesha.security.jwt.JwtUser;

public class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
