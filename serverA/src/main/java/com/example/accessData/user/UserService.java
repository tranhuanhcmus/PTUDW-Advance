package com.example.accessData.user;

import com.example.accessData.auth.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;


@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> login(User user) {
        Optional<User> optionalUser = userRepository.findByUserName(user.getuserName());

        Base64.Encoder encoder = Base64.getEncoder();

        if (optionalUser.isPresent()) {
            User dbUser = optionalUser.get();
            if (user.getpassword().equals(dbUser.getpassword())) {
                // Create a JWT token with the user information

                long currentTime = System.currentTimeMillis();

                String token = Jwts.builder()
                        .claim("userName", dbUser.getuserName())
                        .claim("password", dbUser.getpassword())
                        .claim("time",currentTime)
                        .signWith(SignatureAlgorithm.HS256, AuthenticationService.SECRET_KEY)
                        .compact();

                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Invalid", HttpStatus.BAD_REQUEST);
    }
}
