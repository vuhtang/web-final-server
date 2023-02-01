package com.vuhtang.lesha.controller;

import com.vuhtang.lesha.model.AuthCreds;
import com.vuhtang.lesha.model.Token;
import com.vuhtang.lesha.security.jwt.JwtAuthException;
import com.vuhtang.lesha.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth/")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(@Qualifier("AuthServiceImpl") AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody AuthCreds authCreds) {
        try {
            Token token = authService.login(authCreds);
            return ResponseEntity.ok(token);
        } catch (JwtAuthException e) {
            return ResponseEntity
                    .status(HttpStatus.MULTI_STATUS)
                    .body(new Token("", "Invalid username or password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody AuthCreds authCreds) {
        try {
            authService.register(authCreds);
            Token token = authService.login(authCreds);
            return ResponseEntity.ok(token);
        } catch (JwtAuthException e) {
            return ResponseEntity
                    .status(HttpStatus.MULTI_STATUS)
                    .body(new Token("", e.getMessage()));
        }
    }
}
