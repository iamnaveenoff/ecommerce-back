package com.expeditors.ecommerce.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDTO {

    private String token;
    private  String refreshToken;
    private  CustomerDTO userDetails;

}
