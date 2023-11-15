package com.example.accessData.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {
    public static final String SECRET_KEY = "SecretKey";
    public static final long MAX_TIME_DIFFERENCE_MINUTES = 60;

    public static boolean isTimeValid(long clientTime) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = Math.abs(currentTime - clientTime);
        long timeDifferenceMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference);

        return timeDifferenceMinutes <= MAX_TIME_DIFFERENCE_MINUTES;
    }

    public static String generateHash(String url, String timestamp, String secretKey) {
        try {
            String dataToHash = url + timestamp + secretKey;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(dataToHash.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Authentication getAuthentication(HttpServletRequest request) {
        if (request.getHeader("Authorization") != null) {
            String accessToken = request.getHeader("Authorization");
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody();

            String userName = claims.get("userName", String.class);
            String password = claims.get("password", String.class);
            long timestamp = claims.get("time", Long.class);

            String token = Jwts.builder()
                    .claim("userName", userName)
                    .claim("password", password)
                    .claim("time", timestamp)
                    .signWith(SignatureAlgorithm.HS256, AuthenticationService.SECRET_KEY)
                    .compact();

            System.out.println(userName);
            System.out.println(password);
            System.out.println(timestamp);
            System.out.println(accessToken);
            System.out.println(token);

            if (!accessToken.equals(token)) {
                throw new BadCredentialsException("Invalid Access Token");
            }
            if (!isTimeValid(timestamp)) {
                throw new BadCredentialsException("Invalid Time");
            }

            return new ApiKeyAuthentication(accessToken, AuthorityUtils.NO_AUTHORITIES);
        }
        throw new BadCredentialsException("No Access Token");

    }

}