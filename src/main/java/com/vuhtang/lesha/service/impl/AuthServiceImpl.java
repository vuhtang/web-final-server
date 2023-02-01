package com.vuhtang.lesha.service.impl;

import com.vuhtang.lesha.model.AuthCreds;
import com.vuhtang.lesha.model.Token;
import com.vuhtang.lesha.model.User;
import com.vuhtang.lesha.security.jwt.JwtAuthException;
import com.vuhtang.lesha.security.jwt.JwtProvider;
import com.vuhtang.lesha.service.AuthService;
import com.vuhtang.lesha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("AuthServiceImpl")
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final BCryptPasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthServiceImpl(UserService userService, BCryptPasswordEncoder encoder, JwtProvider jwtProvider) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Token login(AuthCreds creds) throws JwtAuthException {
        User user = userService.findByUsername(creds.getUsername());
        if (user == null)
            throw new JwtAuthException("User with this username doesn't exist");
        if (!encoder.matches(creds.getPassword(), user.getPassword()))
            throw new JwtAuthException("Incorrect password");
        String t = jwtProvider.createToken(user.getUsername());
        return new Token(t, "success");
    }

    @Override
    public void register(AuthCreds creds) throws JwtAuthException {
        if (userService.findByUsername(creds.getUsername()) != null)
            throw new JwtAuthException("User with specified name already exists");
        User user = new User(0L, creds.getUsername(), creds.getPassword());
        userService.registerUser(user);
    }
}
