package com.expeditors.ecommerce.controller;

import com.expeditors.ecommerce.dto.ProductDTO;
import com.expeditors.ecommerce.entities.CustomerEntities;
import com.expeditors.ecommerce.entities.Product;
import com.expeditors.ecommerce.services.ProductService;
import com.expeditors.ecommerce.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<?> uploadProduct(
            @RequestParam String productName,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam String contactNumber,
            @RequestParam Long categoryId,
            @RequestParam MultipartFile image) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerEntities currentUser = (CustomerEntities) authentication.getPrincipal();


        try {
            Product product = productService.publishProduct(productName, description, price, contactNumber, categoryId, image, currentUser);

            ResponseMessage<Product> response = new ResponseMessage<>(HttpStatus.OK.value(), "Product uploaded successfully", product, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to upload product", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long productId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String contactNumber,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) MultipartFile image) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerEntities currentUser = (CustomerEntities) authentication.getPrincipal();

        try {
            productService.updateProduct(productId, productName, description, price, contactNumber, categoryId, image, currentUser);

            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.OK.value(), "Product updated successfully", null, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update product", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerEntities currentUser = (CustomerEntities) authentication.getPrincipal();

        try {
            productService.deleteProduct(productId, currentUser);

            // Create a success response
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.OK.value(), "Product deleted successfully", null, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Create an error response
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to delete product", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductDetails(productId);
            ProductDTO productDTO = new ProductDTO(product); // Convert entity to DTO
            ResponseMessage<ProductDTO> response = new ResponseMessage<>(HttpStatus.OK.value(), "Product retrieved successfully", productDTO, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.NOT_FOUND.value(), "Product not found", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductDTO> products = productService.getAllProducts();
            ResponseMessage<List<ProductDTO>> response = new ResponseMessage<>(
                    HttpStatus.OK.value(),
                    "Products retrieved successfully",
                    products,
                    null
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Failed to retrieve products",
                    null,
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
