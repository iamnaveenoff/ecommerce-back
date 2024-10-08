package com.expeditors.ecommerce.services.impl;

import com.expeditors.ecommerce.dto.*;
import com.expeditors.ecommerce.entities.CustomerEntities;
import com.expeditors.ecommerce.enums.Role;
import com.expeditors.ecommerce.repository.UserRepository;
import com.expeditors.ecommerce.services.AuthenticationService;
import com.expeditors.ecommerce.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public CustomerEntities signUp(SignUpRequestDTO signUpRequestDTO) {
        // Check if email already exists
        if (userRepository.existsByEmail(signUpRequestDTO.getEmail())) {
            throw new IllegalArgumentException("User email already exists.");
        }

        // Check if GSC ID already exists
        if (userRepository.existsByGscId(signUpRequestDTO.getGscId())) {
            throw new IllegalArgumentException("GSC ID already exists.");
        }

        // If both checks pass, proceed to create a new user
        CustomerEntities user = new CustomerEntities();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setName(signUpRequestDTO.getName());
        user.setPhone(signUpRequestDTO.getPhone());
        user.setGscId(signUpRequestDTO.getGscId());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponseDTO signin(SigninRequestDTO signinRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequestDTO.getEmail(), signinRequestDTO.getPassword())
        );

        var user = userRepository.findByEmail(signinRequestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password."));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        CustomerDTO userDetails = new CustomerDTO();
        userDetails.setId(user.getId());
        userDetails.setName(user.getName());
        userDetails.setGscId(user.getGscId());
        userDetails.setPhone(user.getPhone());
        userDetails.setEmail(user.getEmail());
        userDetails.setRole(user.getRole().name());

        JwtAuthenticationResponseDTO jwtAuthenticationResponse = new JwtAuthenticationResponseDTO();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setUserDetails(userDetails);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        String userEmail = jwtService.extractUserName(refreshTokenRequestDTO.getToken());
        CustomerEntities user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequestDTO.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponseDTO jwtAuthenticationResponseDTO = new JwtAuthenticationResponseDTO();
            jwtAuthenticationResponseDTO.setToken(jwt);
            jwtAuthenticationResponseDTO.setRefreshToken(refreshTokenRequestDTO.getToken());
            return jwtAuthenticationResponseDTO;
        }
        return null;
    }
}
