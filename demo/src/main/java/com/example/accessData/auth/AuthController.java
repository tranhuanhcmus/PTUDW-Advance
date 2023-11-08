package com.example.accessData.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/auth")
@Tag(name = "Authentication", description = "authenticate user")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/")
    public ResponseEntity<LoginForm> login(@RequestBody @Valid LoginForm form) {
        return new ResponseEntity<>(form,HttpStatus.OK);
    }
}
