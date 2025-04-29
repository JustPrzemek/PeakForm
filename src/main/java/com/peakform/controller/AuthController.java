package com.peakform.controller;

import com.peakform.dto.AuthResponse;
import com.peakform.dto.LoginRequest;
import com.peakform.dto.RegistrationRequest;
import com.peakform.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication API")
public interface AuthController {

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account with provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration successful",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid registration data")
    })
    ResponseEntity<AuthResponse> register(@Valid @RequestBody RegistrationRequest request);

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticates user and returns JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request);

    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Returns the authenticated user's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String authHeader);

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Generates a new access token using refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid refresh token")
    })
    ResponseEntity<AuthResponse> refreshToken(@RequestParam String refreshToken);

    @GetMapping("/verify-email")
    @Operation(summary = "Verify email", description = "Verifies user's email using verification token")
    ResponseEntity<String> verifyEmail(@RequestParam String token);

    @PostMapping("/request-password-reset")
    @Operation(summary = "Request password reset", description = "Sends password reset link to user's email")
    ResponseEntity<String> requestPasswordReset(@RequestParam String email);

    @PostMapping("/reset-password")
    @Operation(summary = "Reset password", description = "Resets user's password using reset token")
    ResponseEntity<String> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword);
}
