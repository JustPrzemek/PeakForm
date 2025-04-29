package com.peakform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class LoginRequest {

    @NotBlank
    @Email
    @Schema(description = "Email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank
    @Schema(description = "Password", example = "password123", required = true)
    private String password;

}