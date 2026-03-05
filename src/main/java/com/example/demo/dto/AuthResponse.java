package com.example.demo.dto;

public class AuthResponse {

    private String message;
    private long userId;
    private String email;
    private String accessToken;

    public AuthResponse(String message, long userId, String email, String accessToken) {
        this.message = message;
        this.userId = userId;
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
