package com.expeditors.ecommerce.services;

import com.expeditors.ecommerce.dto.JwtAuthenticationResponseDTO;
import com.expeditors.ecommerce.dto.RefreshTokenRequestDTO;
import com.expeditors.ecommerce.dto.SignUpRequestDTO;
import com.expeditors.ecommerce.dto.SigninRequestDTO;
import com.expeditors.ecommerce.entities.CustomerEntities;

public interface AuthenticationService {

    public CustomerEntities signUp(SignUpRequestDTO signUpRequestDTO);

    JwtAuthenticationResponseDTO signin(SigninRequestDTO signinRequestDTO);
    JwtAuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
