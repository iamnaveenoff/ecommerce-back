package com.expeditors.ecommerce.controller;

import com.expeditors.ecommerce.dto.JwtAuthenticationResponseDTO;
import com.expeditors.ecommerce.dto.RefreshTokenRequestDTO;
import com.expeditors.ecommerce.dto.SignUpRequestDTO;
import com.expeditors.ecommerce.dto.SigninRequestDTO;
import com.expeditors.ecommerce.entities.CustomerEntities;
import com.expeditors.ecommerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private  final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<CustomerEntities> signup(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequestDTO));
    }

    @PostMapping("/signin")
    public  ResponseEntity<JwtAuthenticationResponseDTO> signin(@RequestBody SigninRequestDTO signinRequestDTO){
        return  ResponseEntity.ok(authenticationService.signin(signinRequestDTO));
    }

    @PostMapping("/refresh")
    public  ResponseEntity<JwtAuthenticationResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return  ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDTO));
    }
}
