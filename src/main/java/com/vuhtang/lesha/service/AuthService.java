package com.vuhtang.lesha.service;

import com.vuhtang.lesha.model.AuthCreds;
import com.vuhtang.lesha.model.Token;
import com.vuhtang.lesha.security.jwt.JwtAuthException;

public interface AuthService {
    Token login(AuthCreds creds) throws JwtAuthException;

    void register(AuthCreds creds) throws JwtAuthException;
}
