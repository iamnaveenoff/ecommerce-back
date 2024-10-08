package com.expeditors.ecommerce.dto;

import com.expeditors.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private String contactNumber;
    private byte[] image;
    private String categoryName;
    private long categoryId;
    private String publishedBy;
    private String email;
    private boolean published;

    public ProductDTO(Product product) {
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.contactNumber = product.getContactNumber();
        this.image = product.getProductImage();
        this.categoryName = product.getCategory() != null ? product.getCategory().getCategoryName() : null; // Fetch category name
        this.categoryId = product.getCategory() != null ? product.getCategory().getId() : null; // Fetch category ID
        this.publishedBy = product.getCustomer() != null ? product.getCustomer().getName() : null; // Assuming Customer has a getName method
        this.email = product.getCustomer() != null ? product.getCustomer().getEmail() : null; // Assuming Customer has a getEmail method
        this.published = product.isPublished();
    }
}
