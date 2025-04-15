package com.peakform.dto;

import io.swagger.v3.oas.annotations.media.Schema;

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

    // Constructors
    public UserDto() {
    }

    public UserDto(Long id, String email, String username, Integer age, Float weight, Float height, String goal, String role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.role = role;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
