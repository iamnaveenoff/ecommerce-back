package com.expeditors.ecommerce.services.impl;

import com.expeditors.ecommerce.dto.ProductDTO;
import com.expeditors.ecommerce.entities.Category;
import com.expeditors.ecommerce.entities.Customer;
import com.expeditors.ecommerce.entities.Product;
import com.expeditors.ecommerce.enums.Role;
import com.expeditors.ecommerce.repository.CategoryRepository;
import com.expeditors.ecommerce.repository.ProductRepository;
import com.expeditors.ecommerce.services.ImageStorageService;
import com.expeditors.ecommerce.services.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageStorageService imageStorageService;


    public Product publishProduct(String productName, String description, Double price, String contactNumber, Long categoryId, MultipartFile image, Customer customer) throws IOException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Save the product first to generate the ID
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setContactNumber(contactNumber);
        product.setCategory(category);
        product.setCustomer(customer);
        product.setPublished(true);
        // Convert image file to byte array
        product.setProductImage(image.getBytes());
        Product savedProduct = productRepository.save(product);

        /*// Now store the image with the product ID in the name
        String imagePath = imageStorageService.storeProductImage(image, savedProduct.getId());
        savedProduct.setImagePath(imagePath);*/

        return productRepository.save(savedProduct);
    }

    public void updateProduct(Long productId, String productName, String description, Double price, String contactNumber, Long categoryId, MultipartFile image, Customer currentUser) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the user is authorized to edit the product
        if (!product.getCustomer().equals(currentUser) && !currentUser.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("You are not authorized to edit this product");
        }

        // Update the product details if provided
        if (StringUtils.isNotEmpty(productName)) {
            product.setProductName(productName);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (contactNumber != null) {
            product.setContactNumber(contactNumber);
        }
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        if (image != null && !image.isEmpty()) {
//            String imagePath = imageStorageService.storeProductImage(image, productId);
            product.setProductImage(image.getBytes());
        }

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId, Customer currentUser) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getCustomer().equals(currentUser) && !currentUser.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("You are not authorized to delete this product");
        }

        productRepository.delete(product);
    }

    public Product getProductDetails(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getContactNumber(),
                product.getProductImage(),
                product.getCategory().getCategoryName(),
                product.getCategory().getId(),
                product.getCustomer().getName(),
                product.getCustomer().getEmail(),
                product.isPublished()
        )).collect(Collectors.toList());
    }
}
