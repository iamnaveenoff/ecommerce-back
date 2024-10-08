package com.expeditors.ecommerce.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {

    private  String name;
    private  String email;
    private  String gscId;
    private  String phone;
    private  String password;
}
