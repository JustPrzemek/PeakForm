package com.peakform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegistrationRequest {

    @NotBlank
    @Email
    @Schema(description = "Email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank
    @Size(min = 8)
    @Schema(description = "Password", example = "password123", required = true)
    private String password;

    @NotBlank
    @Schema(description = "Username", example = "john_doe", required = true)
    private String username;

    @Positive
    @Schema(description = "Age", example = "25")
    private Integer age;

    @Positive
    @Schema(description = "Weight in kg", example = "75.5")
    private Float weight;

    @Positive
    @Schema(description = "Height in cm", example = "180.0")
    private Float height;

    @Pattern(regexp = "reduction|bulk|maintenance")
    @Schema(description = "Fitness goal", example = "maintenance", allowableValues = {"reduction", "bulk", "maintenance"})
    private String goal;

}