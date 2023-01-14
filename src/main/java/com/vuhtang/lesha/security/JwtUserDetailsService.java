package com.vuhtang.lesha.security;

import com.vuhtang.lesha.model.User;
import com.vuhtang.lesha.security.jwt.JwtUser;
import com.vuhtang.lesha.service.UserService;
import com.vuhtang.lesha.util.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return JwtUserFactory.create(user);
    }
}
