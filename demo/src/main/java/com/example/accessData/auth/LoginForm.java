package com.example.accessData.auth;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class LoginForm implements Serializable {
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
    public LoginForm() {

    }
    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
