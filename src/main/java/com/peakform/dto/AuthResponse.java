package com.peakform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class AuthResponse {

    @Schema(description = "JWT Access Token")
    private String token;

    @Schema(description = "JWT Refresh Token")
    private String refreshToken;

    @Schema(description = "User ID")
    private Long userId;

    @Schema(description = "Username")
    private String username;

}