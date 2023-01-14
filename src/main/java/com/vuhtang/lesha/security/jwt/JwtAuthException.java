package com.vuhtang.lesha.security.jwt;

import javax.naming.AuthenticationException;

public class JwtAuthException extends AuthenticationException {
    public JwtAuthException(String explanation) {
        super(explanation);
    }
}
