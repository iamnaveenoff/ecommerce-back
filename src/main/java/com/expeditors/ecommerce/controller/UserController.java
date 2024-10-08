package com.expeditors.ecommerce.controller;

import com.expeditors.ecommerce.dto.CustomerDTO;
import com.expeditors.ecommerce.dto.ProductDTO;
import com.expeditors.ecommerce.services.UserService;
import com.expeditors.ecommerce.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

/*    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<CustomerDTO> customers = userService.getAllCustomers();
            ResponseMessage<List<CustomerDTO>> response = new ResponseMessage<>(
                    HttpStatus.OK.value(),
                    "Customer retrieved successfully",
                    customers,
                    null
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Failed to retrieve users",
                    null,
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }*/
}
