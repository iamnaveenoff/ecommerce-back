package com.expeditors.ecommerce.services;

import com.expeditors.ecommerce.dto.CustomerDTO;
import com.expeditors.ecommerce.dto.ProductDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDetailsService userDetailsService();

//    public List<CustomerDTO> getAllCustomers();
}
