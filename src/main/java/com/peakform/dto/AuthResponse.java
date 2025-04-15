package com.peakform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthResponse {
    @Schema(description = "JWT Access Token")
    private String token;

    @Schema(description = "JWT Refresh Token")
    private String refreshToken;

    @Schema(description = "User ID")
    private Long userId;

    @Schema(description = "Username")
    private String username;

    // Constructors
    public AuthResponse() {
    }

    public AuthResponse(String token, String refreshToken, Long userId, String username) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.username = username;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
