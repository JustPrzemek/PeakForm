package com.peakform.service;

import com.peakform.dto.AuthResponse;
import com.peakform.dto.LoginRequest;
import com.peakform.dto.RegistrationRequest;
import com.peakform.dto.UserDto;
import com.peakform.exception.BadRequestException;
import com.peakform.exception.ResourceNotFoundException;
import com.peakform.model.User;
import com.peakform.repository.UserRepository;
import com.peakform.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegistrationRequest request) {
        // Check if email is already in use
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already in use");
        }

        // Create new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setAge(request.getAge());
        user.setWeight(request.getWeight());
        user.setHeight(request.getHeight());
        user.setGoal(request.getGoal());
        user.setAuthProvider("local");
        user.setRole("USER");
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        // Generate tokens
        String token = jwtTokenProvider.generateTokenWithId(
                savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
        String refreshToken = jwtTokenProvider.generateRefreshToken(savedUser.getEmail());

        // Save refresh token
        savedUser.setRefreshToken(refreshToken);
        userRepository.save(savedUser);

        return new AuthResponse(token, refreshToken, savedUser.getId(), savedUser.getUsername());
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get user details
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", request.getEmail()));

        // Generate tokens
        String token = jwtTokenProvider.generateToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        // Save refresh token
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new AuthResponse(token, refreshToken, user.getId(), user.getUsername());
    }

    public UserDto getCurrentUser(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtTokenProvider.getUsernameFromToken(token);

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

            return new UserDto(
                    user.getId(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getAge(),
                    user.getWeight(),
                    user.getHeight(),
                    user.getGoal(),
                    user.getRole()
            );
        }
        throw new BadRequestException("Authorization header must start with Bearer");
    }

    public AuthResponse refreshToken(String refreshToken) {
        // Validate refresh token
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new BadRequestException("Invalid refresh token");
        }

        String email = jwtTokenProvider.getUsernameFromToken(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        // Check if the refresh token matches the stored one
        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new BadRequestException("Refresh token does not match");
        }

        // Generate new tokens
        String newToken = jwtTokenProvider.generateTokenWithId(
                user.getId(), user.getEmail(), user.getRole());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        // Save new refresh token
        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        return new AuthResponse(newToken, newRefreshToken, user.getId(), user.getUsername());
    }
}
