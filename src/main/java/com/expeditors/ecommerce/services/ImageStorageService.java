package com.expeditors.ecommerce.services;

import io.jsonwebtoken.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface ImageStorageService {

    public String storeProductImage(MultipartFile file, Long productId) throws IOException, java.io.IOException;
    String storeCategoryImage(MultipartFile file) throws IOException, java.io.IOException;
}
