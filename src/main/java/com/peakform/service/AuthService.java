package com.peakform.service;

import com.peakform.dto.AuthResponse;
import com.peakform.dto.LoginRequest;
import com.peakform.dto.UserRegistrationDto;
import com.peakform.exception.AuthException;
import com.peakform.model.User;
import com.peakform.repository.UserRepository;
import com.peakform.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse registerUser(UserRegistrationDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .age(request.getAge())
                .weight(request.getWeight())
                .height(request.getHeight())
                .goal(request.getGoal())
                .emailVerified(false)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(UserPrincipal.create(user));
        return new AuthResponse(jwtToken);
    }

    public AuthResponse loginUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found"));

        String jwtToken = jwtService.generateToken(UserPrincipal.create(user));
        return new AuthResponse(jwtToken);
    }
}
