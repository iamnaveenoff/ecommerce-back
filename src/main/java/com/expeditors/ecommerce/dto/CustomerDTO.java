package com.expeditors.ecommerce.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Integer id;
    private String name;
    private String phone;
    private String gscId;
    private String email;
    private String role;
}
