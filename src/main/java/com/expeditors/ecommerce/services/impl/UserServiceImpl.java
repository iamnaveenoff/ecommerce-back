package com.expeditors.ecommerce.services.impl;

import com.expeditors.ecommerce.dto.CustomerDTO;
import com.expeditors.ecommerce.dto.ProductDTO;
import com.expeditors.ecommerce.entities.Customer;
import com.expeditors.ecommerce.entities.Product;
import com.expeditors.ecommerce.repository.UserRepository;
import com.expeditors.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            }
        };
    }

/*    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = userRepository.findAll();
        return customers.stream().map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getGscId(),
                customer.getEmail(),
                customer.getRole()
        )).collect(Collectors.toList());
    }*/
}
