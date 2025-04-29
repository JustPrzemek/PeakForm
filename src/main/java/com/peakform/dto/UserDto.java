package com.peakform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserDto {

    @Schema(description = "User ID")
    private Long id;

    @Schema(description = "Email address")
    private String email;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "Age")
    private Integer age;

    @Schema(description = "Weight in kg")
    private Float weight;

    @Schema(description = "Height in cm")
    private Float height;

    @Schema(description = "Fitness goal")
    private String goal;

    @Schema(description = "User role")
    private String role;

}