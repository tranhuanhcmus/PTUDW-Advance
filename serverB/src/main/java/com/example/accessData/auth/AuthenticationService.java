package com.example.accessData.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "TOKEN";
    private static final String SECRET_KEY = "Baeldung";
    private static final String TIME_STRING ="TIME";

    public static boolean isTimeValid(String clientTimeStr, long validityPeriod) {
        long currentTime = System.currentTimeMillis();

        
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date clientTime = dateFormat.parse(clientTimeStr);
            long clientTimeMillis = clientTime.getTime();
           
            long timeDifference = currentTime - clientTimeMillis;
          

            return timeDifference >= 0 && timeDifference <= validityPeriod;
        } catch (ParseException e) {
            // Handle parsing error if the clientTime string format is incorrect.
            return false;
        }
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
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        String timeString = request.getHeader(TIME_STRING);
      
     

        long validityPeriod = 6000000; // Example: 1 minute
        boolean isTimeValid = isTimeValid(timeString, validityPeriod);
        System.out.println(isTimeValid);
       
    

        if (isTimeValid) {
        	String url = request.getRequestURI();
           
            String hashserver = generateHash(url, timeString, SECRET_KEY);
            System.out.println(apiKey);
            System.out.println(hashserver);
            if (apiKey == null || !apiKey.equals(hashserver)) {
                throw new BadCredentialsException("Invalid API Key");
            }
            
        } else {
        	  throw new BadCredentialsException("Invalid time");
        }
       

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
    
    public static void main(String[] args) {
        String url = "/api/v1/actors/";
        String timestamp = "2023-11-09T02:04:00.000Z"; // Replace with the actual timestamp
        

        String hash = generateHash(url, timestamp, SECRET_KEY);
        System.out.println("Generated Hash: " + hash);
    }

}