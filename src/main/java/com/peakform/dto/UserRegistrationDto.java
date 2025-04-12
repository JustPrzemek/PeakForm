package com.peakform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String username;

    @Min(1)
    private Integer age;

    @Min(1)
    private Float weight;

    @Min(1)
    private Float height;

    @NotBlank
    private String goal;
}