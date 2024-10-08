package com.expeditors.ecommerce.services;

import com.expeditors.ecommerce.dto.ProductDTO;
import com.expeditors.ecommerce.entities.Customer;
import com.expeditors.ecommerce.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    public Product publishProduct(String productName, String description, Double price, String contactNumber, Long categoryId, MultipartFile image, Customer customer) throws IOException;

    public void updateProduct(Long productId, String productName, String description, Double price, String contactNumber, Long categoryId, MultipartFile image, Customer currentUser) throws IOException;

    public void deleteProduct(Long productId, Customer currentUser);

    public Product getProductDetails(Long productId);

    public List<ProductDTO> getAllProducts();
}
