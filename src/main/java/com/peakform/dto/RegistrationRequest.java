package com.peakform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;

public class RegistrationRequest {
    @NotBlank
    @Email
    @Schema(description = "Email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank
    @Size(min = 6)
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

    // Constructors
    public RegistrationRequest() {
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
